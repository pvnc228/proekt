using System;
using System.Collections.Generic;


public class Category
{
    public int Id { get; set; }
    public string Name { get; set; }

    // Навигационное свойство для связи с записями дневника
    public List<DiaryEntry> DiaryEntries { get; set; }
}

public class User
{
    public int Id { get; set; }
    public string Username { get; set; }
    public string Email { get; set; }
    public string Password { get; set; }

    // Навигационное свойство для связи с записями дневника пользователя
    public List<DiaryEntry> DiaryEntries { get; set; }
}
