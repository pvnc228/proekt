﻿namespace AuthorizationService.Models
{
     public class LoginRequest
    {
        public string Username { get; set; }
        public string Password { get; set; }
    }

    public class EncryptRequest
    {
        public string Data { get; set; }
    }
}
