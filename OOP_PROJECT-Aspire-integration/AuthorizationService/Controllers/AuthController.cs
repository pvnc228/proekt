using Microsoft.AspNetCore.Mvc;
using Microsoft.IdentityModel.Tokens;
using AuthorizationService.Models;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using System.Net.Http;
using PasswordManagerService.Models;

namespace NewAuthService.Controllers
{
    [Route("api/[controller]")]
    [ApiController]


    public class AuthController : ControllerBase
    {
        private readonly IConfiguration _configuration;
        private static readonly List<User> Users = new List<User>(); // Простая коллекция для хранения пользователей

        private readonly PasswordService _passwordService;

    public AuthController(PasswordService passwordService)
    {
        _passwordService = passwordService;
    }


        [HttpPost("register")]
        /*public IActionResult Register([FromBody] RegisterRequest request)
        {
            
            if (Users.Exists(u => u.Username == request.Username))
            {
                return BadRequest("User already exists.");
            }

            Users.Add(new User { Username = request.Username, Password = request.Password });
            return Ok("User registered successfully.");
        }*/
        public async Task<IActionResult> Register([FromBody] RegisterRequest request)
        {
            // Логика регистрации пользователя

            var passwordModel = new PasswordModel(request.Username, request.Password);

            var success = await _passwordService.StorePasswordAsync(passwordModel);

            if (success)
            {
                return Ok("User registered successfully.");
            }

            return StatusCode(500, "Error storing password.");
        }

        [HttpPost("login")]
        public IActionResult Login([FromBody] LoginRequest request)
        {
            if (IsValidUser(request))
            {
                var token = GenerateJwtToken(request.Username);
                return Ok(new { Token = token });
            }

            return Unauthorized();
        }

        private bool IsValidUser(LoginRequest request)
        {
            return Users.Exists(u => u.Username == request.Username && u.Password == request.Password);
        }

        private string GenerateJwtToken(string username)
        {
            var jwtConfig = _configuration.GetSection("Jwt");
            var securityKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(jwtConfig["Key"]));
            var credentials = new SigningCredentials(securityKey, SecurityAlgorithms.HmacSha256);

            var claims = new[]
            {
            new Claim(JwtRegisteredClaimNames.Sub, username),
            new Claim(JwtRegisteredClaimNames.Jti, Guid.NewGuid().ToString())
        };

            var token = new JwtSecurityToken(
                issuer: jwtConfig["Issuer"],
                audience: jwtConfig["Audience"],
                claims: claims,
                expires: DateTime.Now.AddMinutes(double.Parse(jwtConfig["ExpiryMinutes"])),
                signingCredentials: credentials
            );

            return new JwtSecurityTokenHandler().WriteToken(token);
        }
    }
}
