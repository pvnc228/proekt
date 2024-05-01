using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
// Замените YourAppName на имя вашего проекта

[Route("api/[controller]")]
[ApiController]
public class DiaryEntriesController : ControllerBase
{
    private readonly DbContext _context;

    public DiaryEntriesController(DbContext context)
    {
        _context = context;
    }

    [HttpGet]
    public async Task<ActionResult<IEnumerable<DiaryEntry>>> GetDiaryEntries() => await _context.DiaryEntries.ToListAsync();

    [HttpGet("{id}")]
    public async Task<ActionResult<DiaryEntry>> GetDiaryEntry(int id)
    {
        var diaryEntry = await _context.DiaryEntries.FindAsync(id);

        if (diaryEntry == null)
        {
            return NotFound();
        }

        return diaryEntry;
    }

    [HttpPost]
    public async Task<ActionResult<DiaryEntry>> PostDiaryEntry(DiaryEntry diaryEntry)
    {
        diaryEntry.CreatedAt = DateTime.Now;
        _context.DiaryEntries.Add(diaryEntry);
        await _context.SaveChangesAsync();

        return CreatedAtAction(nameof(GetDiaryEntry), new { id = diaryEntry.Id }, diaryEntry);
    }

    [HttpDelete("{id}")]
    public async Task<IActionResult> DeleteDiaryEntry(int id)
    {
        var diaryEntry = await _context.DiaryEntries.FindAsync(id);
        if (diaryEntry == null)
        {
            return NotFound();
        }

        _context.DiaryEntries.Remove(diaryEntry);
        await _context.SaveChangesAsync();

        return NoContent();
    }
}
