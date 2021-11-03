using Microsoft.EntityFrameworkCore.Migrations;

namespace Booking.Migrations.Migrations
{
    public partial class AddedAppVersions : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "AppVersions",
                columns: table => new
                {
                    Id = table.Column<decimal>(type: "numeric(20,0)", nullable: false),
                    MajorVersion = table.Column<int>(type: "integer", nullable: false),
                    MinorVersion = table.Column<int>(type: "integer", nullable: false, defaultValue: 0)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_AppVersions", x => x.Id);
                });

            migrationBuilder.InsertData(
                table: "AppVersions",
                columns: new[] { "Id", "MajorVersion", "MinorVersion" },
                values: new object[] { 1m, 0, 1 });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "AppVersions");
        }
    }
}
