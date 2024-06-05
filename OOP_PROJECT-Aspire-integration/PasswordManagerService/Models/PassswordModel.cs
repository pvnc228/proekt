using System.Globalization;
using System.Runtime.CompilerServices;

namespace PasswordManagerService.Models
{
    public class PasswordModel
    {
        public PasswordModel(string str1,string str2) {
            Password = str1;
            Username = str2;
        }
        public string Password { get; set; }
        public string Username { get; set; }
    }
}
