
rem Reboot HUAWEI based GPON with web control
@echo "hola"
set USER=Epadmin
set PASSWLAN=123456789
set PASS=adminEp
set SSID=HUAWEI1
set IP=192.168.18.1

setlocal EnableDelayedExpansion
echo %PASS% | certutil -encode -f - | findstr /v /c:- > pass64.tmp
set /p PASS64=<pass64.tmp
del pass64.tmp

set GPON_URL=http://%IP%
set GPON_RAND_URL=%GPON_URL%/asp/GetRandCount.asp
set GPON_LOGIN_URL=%GPON_URL%/login.cgi
set GPON_WLAN=%GPON_URL%/html/amp/wlanbasic/WlanBasic.asp
set GPON_SET_WLAN=%GPON_URL%/html/amp/wlanbasic/set.cgi?w=InternetGatewayDevice.X_HW_DEBUG.AMP.WifiCoverSetWlanBasic^&y=InternetGatewayDevice.LANDevice.1.WLANConfiguration.1^&z=InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.WPS^&k=InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.PreSharedKey.1^&RequestFile=html/amp/wlanbasic/WlanBasic.asp

rem get initial token
set TOKEN=((Invoke-WebRequest -Uri "%GPON_RAND_URL%" -UseBasicParsing).Content -replace '^\xef\xbb\xbf').Trim()

rem log in and get cookie
if not "!TOKEN!" == "" (

set COOKIE=((Invoke-WebRequest -Uri "%GPON_LOGIN_URL%" -Method POST -Headers @{Cookie="body:Language:english:id=-1"} -UseBasicParsing -Body @{
    UserName = %USER%
    PassWord = %PASS64%
    'x.X_HW_Token' = %TOKEN%
}).Headers['Set-Cookie'] -split '; ' | Where-Object {$_ -like 'sid=*'} | Select-Object -First 1 -replace '^sid=(.*)', '$1').Trim()

) else (
    echo Error: missing initial token & exit /b 1
)

rem get hw token
if not "%COOKIE%" == "" (
    $HWTOKEN = ((Invoke-WebRequest -Uri "%GPON_WLAN%" -UseBasicParsing -Headers @{Cookie = "%COOKIE%"}).Content `
            -match "hwonttoken" `
            -replace '.+value="(\w+)".*', '$1').Trim()
    if not "%HWTOKEN%" == "" (
        Invoke-WebRequest -Uri "%GPON_SET_WLAN%" -Method POST -UseBasicParsing -Headers @{Cookie = "%COOKIE%"} -Body @{
            "y.SSID"          = "%SSID%"
            "w.SSID"          = "%SSID%"
            "k.PreSharedKey"  = "%PASSWLAN%"
            "w.Key"           = "%PASSWLAN%"
            "x.X_HW_Token"    = "%HWTOKEN%"
        } > $null
    ) else (
        echo Error: missing hwtoken & exit /b 1
    )
) else (
        echo Error: missing cookie & exit /b 1
    )

