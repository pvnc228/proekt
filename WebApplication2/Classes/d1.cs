using System;
using System.Collections.Generic;


public class DiaryEntry
{
    public int Id { get; set; }
    public string Title { get; set; }
    public string Description { get; set; }
    public DateTime CreatedAt { get; set; }

    // Внешний ключ для связи с категориями
    public List<Category> Categories { get; set; }
}
