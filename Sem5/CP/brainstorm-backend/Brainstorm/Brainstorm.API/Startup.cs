using Brainstorm.Business.AppVersions.Handlers;
using Brainstorm.Entities;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using MediatR;

namespace Brainstorm.API {
    public class Startup {
    public Startup(IConfiguration configuration) {
            Configuration = configuration;
        }

        private IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services) {
            services.AddControllers();

            services.AddCors(opts => opts.AddDefaultPolicy(
                builder => {
                    builder.AllowAnyMethod()
                        .AllowAnyHeader()
                        .AllowAnyOrigin();
                }));

            services.AddMediatR(typeof(GetVersionQueryHandler));
            services.AddDbContext<BrainstormContext>(opts =>
                opts.UseNpgsql(Configuration.GetConnectionString("SqlConnection")));
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env) {
            if (env.IsDevelopment()) {
                app.UseDeveloperExceptionPage();
            }

            app.UseHttpsRedirection();

            app.UseCors();

            app.UseRouting();

            app.UseAuthorization();

            app.UseEndpoints(endpoints => { endpoints.MapControllers(); });
        }
    }
}