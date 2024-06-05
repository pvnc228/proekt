using Microsoft.EntityFrameworkCore;
using NotesService.Models;

namespace NotesService.Data
{
    public class NotesContext : DbContext
    {
        public NotesContext(DbContextOptions<NotesContext> options) : base(options)
        {
        }

        public DbSet<Note> Notes { get; set; }
    }
}
