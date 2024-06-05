﻿using Microsoft.Data.SqlClient;
using Microsoft.EntityFrameworkCore;
using OOP_PROJECT.Server.Models;


public class ApplicationDbContext : DbContext
{
    public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
    {
            Database.EnsureCreated();
    }

    public DbSet<ProductModel> Products { get; set; }
}