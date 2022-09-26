# DS440-Tutorial

## Enviroment setup for Mac

### Before start

Download the following tools

- [Docker](https://www.docker.com/)
- [Azure Data Studio](https://learn.microsoft.com/en-us/sql/azure-data-studio/download-azure-data-studio?view=sql-server-ver16)
- [VS code (Recomended)](https://code.visualstudio.com/)
- [iTerm2 (Optinal)](https://iterm2.com/)

**If this is your first time to use CLI in Mac**, also install the homebrew and XCode Command line Tools using the following command
```
xcode-select --install
ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```
Restart the terminal session.

### Install SQL Server
Configure at least 4GB of memory for your Docker environment.   
Next, start a new **Terminal prompt** and use the following commands to download and start the **SQL Server on Linux** Docker image. Make sure to use a **strong password with special characters**.
```
docker pull mcr.microsoft.com/mssql/server:2019-latest
docker run -e 'HOMEBREW_NO_ENV_FILTERING=1' -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=yourStrong(!)Password' -p 1433:1433 -d mcr.microsoft.com/mssql/server
```

### Install PHP & Related Package
```
brew install php
brew install autoconf automake libtool openssl
```
You can try the following command to see whether you have successfully installed the PHP.
```
php -v
```

### Install the ODBC Driver and SQL Command Line Utility for SQL Server
SQLCMD is a command line utility that enables you to connect to SQL Server and run queries.
```
brew tap microsoft/mssql-release https://github.com/Microsoft/homebrew-mssql-release
brew update
ACCEPT_EULA=Y brew install msodbcsql17 mssql-tools
```
You can try the following command to see whether you have successfully installed the SQL Server Command Line Utilities.
```
sqlcmd -S 127.0.0.1 -U sa -P your_password -Q "SELECT @@VERSION"
```
The result will be something like
```
----------------------------
Microsoft SQL Server 2019 (RTM-CU17) (KB5016394) - 15.0.4249.2 (X64)
	Jul 22 2022 12:11:33
	Copyright (C) 2019 Microsoft Corporation
	Developer Edition (64-bit) on Linux (Ubuntu 20.04.4 LTS) <X64>

(1 rows affected)
```

### Install the PHP Drivers for SQL Server
```
sudo pecl install pdo_sqlsrv
sudo pecl install sqlsrv
```

### Install the Apache
The latest macOS 12.0 Monterey comes with Apache 2.4 pre-installed. If you already have the built-in Apache running, it will need to be shutdown first. It really doesn't hurt to just run all these commands in order - even if it's a fresh installation:
```
sudo apachectl stop
sudo launchctl unload -w /System/Library/LaunchDaemons/org.apache.httpd.plist 2>/dev/null
```
Now you need to install the new one provided by Brew:
```
brew install httpd
```
Upon completion you should see a message like:
```
/usr/local/Cellar/httpd/2.4.54_1: 1,660 files, 32.0MB
```
Apache is controlled via the brew services command so some useful commands to use are:
```
brew services stop httpd
brew services start httpd
brew services restart httpd
```
After starting the apache server, you can try to reach your server in a browser by pointing it at [http://localhost:8080](http://localhost:8080), you should see a simple header that says **"It works!"**.

### Apache Configuration (Also works for Windows)
In order to make the apache work with PHP and your project, you need to edit Apache's configuration file `/usr/local/etc/httpd/httpd.conf`

Find the line that says
```
Listen 8080
```
and change it to 
```
Listen 80
```
Find the term `DocumentRoot` and you should see the following line:
```
DocumentRoot "/usr/local/var/www"
```
Change this to point to your project
```
DocumentRoot "/Path/To/Your/Project"
```
You also need to change the `<Directory>` tag reference right below the DocumentRoot line. This should also be changed to point to your project root also:
```
<Directory "/Path/To/Your/Project">
```
In that same `<Directory>` block you will find an `AllowOverride` setting, this should be changed as follows:
```
#
# AllowOverride controls what directives may be placed in .htaccess files.
# It can be "All", "None", or any combination of the keywords:
#   AllowOverride FileInfo AuthConfig Limit
#
AllowOverride All
```
Also you should now enable **mod_rewrite** which is commented out by default. Search for `mod_rewrite.so` and uncomment the line. It should be like this:
```
LoadModule rewrite_module lib/httpd/modules/mod_rewrite.so
```
Apache likes to have a server name in the configuration, but this is disabled by default, so search for:
```
#ServerName www.example.com:8080
```
and replace it with
```
ServerName localhost
```


### Apache PHP Setup (Also works for Windows)
Below the uncomment `mod_rewrite.so` line, you add the following `libphp` modules:
```
LoadModule php_module /usr/local/opt/php@8.1/lib/httpd/modules/libphp.so
```
Also you must set the Directory Indexes for PHP explicitly, so search for this block:
```
<IfModule dir_module>
    DirectoryIndex index.html
</IfModule>
```
and replace it with this:
```
<IfModule dir_module>
    DirectoryIndex index.php index.html
</IfModule>

<FilesMatch \.php$>
    SetHandler application/x-httpd-php
</FilesMatch>
```

### Done
Save the file and stop Apache then start again, now that you are all set:
```
brew services restart httpd
```

### References:
- [macOS 12.0 Monterey Apache Setup: Multiple PHP Versions](https://getgrav.org/blog/macos-monterey-apache-multiple-php-versions)
- [Create PHP apps using SQL Server on macOS] (https://www.microsoft.com/en-us/sql-server/developer-get-started/php/mac)
