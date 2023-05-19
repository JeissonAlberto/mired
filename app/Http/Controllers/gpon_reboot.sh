#!/bin/sh
#
# Reboot HUAWEI based GPON with web control
#

IFS=

# echo "Enter GPON username:"
# read USER
USER=Epadmin
PASSWLAN=123456789
PASS=adminEp
SSID=HUAWEI
IP=192.168.18.1


PASS64=$(echo -n "${PASS}" | base64)

GPON_URL="http://${IP}"
GPON_RAND_URL="$GPON_URL/asp/GetRandCount.asp"
GPON_LOGIN_URL="$GPON_URL/login.cgi"
GPON_RESET_URL="$GPON_URL/html/ssmp/reset/reset.asp"
GPON_WLAN="$GPON_URL/html/amp/wlanbasic/WlanBasic.asp"
GPON_WLAN_1="$GPON_URL/html/amp/common/getSsidProcFlag.asp?&1=1"
GPON_REBOOT_URL="$GPON_URL/html/ssmp/reset/set.cgi?x=InternetGatewayDevice.X_HW_DEBUG.SMP.DM.ResetBoard&RequestFile=html/ssmp/reset/reset.asp"
GPON_SET_WLAN="$GPON_URL/html/amp/wlanbasic/set.cgi?w=InternetGatewayDevice.X_HW_DEBUG.AMP.WifiCoverSetWlanBasic&y=InternetGatewayDevice.LANDevice.1.WLANConfiguration.1&z=InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.WPS&k=InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.PreSharedKey.1&RequestFile=html/amp/wlanbasic/WlanBasic.asp"

# get initial token
TOKEN=$(curl -Ss "${GPON_RAND_URL}" | sed 's|^\xef\xbb\xbf||')

# log in and get cookie
if [ -n "${TOKEN}" ]; then
    COOKIE=$(curl -c - -Ss  \
        -b "Cookie=body:Language:english:id=-1" \
        -d "UserName=${USER}" \
        -d "PassWord=${PASS64}" \
        -d "x.X_HW_Token=${TOKEN}" \
        "${GPON_LOGIN_URL}" | egrep "Cookie[[:space:]]+sid=" | awk '{print $NF}')
else
    echo "Error: missing initial token" && exit 1
fi
echo ${COOKIE}
# get hw token
if [ -n "${COOKIE}" ]; then
    HWTOKEN=$(curl -Ss \
        -b "Cookie=${COOKIE}" \
        "${GPON_WLAN}" \
        | grep "hwonttoken" | sed -E 's|^.+value="(\w+)">.*|\1|')
        echo "token" + ${HWTOKEN}
    HWTOKEN1=$(curl -Ss \
        -b "Cookie=${COOKIE}" \
        -d "wlanid=1" \
        "${GPON_WLAN_1}" \
        )
        echo "token" + ${HWTOKEN1}
    HWTOKEN2=$(curl -Ss \
        -b "Cookie=${COOKIE}" \
        -b "Upgrade-Insecure-Requests: 1" \
        -b "Content-Type: application/x-www-form-urlencoded" \
        -d "y.SSID=${SSID}" \
        -d "w.SSID=${SSID}" \
        -d "k.PreSharedKey=${PASSWLAN}" \
        -d "w.Key=${PASSWLAN}" \
        -d "x.X_HW_Token=${HWTOKEN}" \
        "${GPON_SET_WLAN}" \
        )

else
    echo "Error: missing session cookie" && exit 1
fi

echo "token" + ${GPON_SET_WLAN}
