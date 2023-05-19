<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use GuzzleHttp\Client;

class HomeController extends Controller
{
    public function index()
    {

       /*  $USER = 'Epadmin';
        $PASSWLAN = '123456789';
        $PASS = 'adminEp';
        $SSID = 'HUAWEI1';
        $IP = '192.168.18.1';

        $PASS64 = base64_encode($PASS);

        $GPON_URL = "http://${IP}";
        $GPON_RAND_URL = "${GPON_URL}/asp/GetRandCount.asp";
        $GPON_LOGIN_URL = "${GPON_URL}/login.cgi";
        $GPON_RESET_URL = "${GPON_URL}/html/ssmp/reset/reset.asp";
        $GPON_WLAN = "${GPON_URL}/html/amp/wlanbasic/WlanBasic.asp";
        $GPON_WLAN_1 = "${GPON_URL}/html/amp/common/getSsidProcFlag.asp?&1=1";
        $GPON_REBOOT_URL = "${GPON_URL}/html/ssmp/reset/set.cgi?x=InternetGatewayDevice.X_HW_DEBUG.SMP.DM.ResetBoard&RequestFile=html/ssmp/reset/reset.asp";
        $GPON_SET_WLAN = "${GPON_URL}/html/amp/wlanbasic/set.cgi?w=InternetGatewayDevice.X_HW_DEBUG.AMP.WifiCoverSetWlanBasic&y=InternetGatewayDevice.LANDevice.1.WLANConfiguration.1&z=InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.WPS&k=InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.PreSharedKey.1&RequestFile=html/amp/wlanbasic/WlanBasic.asp";

        $user = "Epadmin";
        $passwlan = "123456789";
        $pass = "adminEp";
        $ssid = "HUAWEI1";
        $ip = "192.168.18.1";
        $pass64 = base64_encode($pass);

        $gponUrl = "http://".$ip;
        $gponRandUrl = $gponUrl."/asp/GetRandCount.asp";
        $gponLoginUrl = $gponUrl."/login.cgi";
        $gponWlan = $gponUrl."/html/amp/wlanbasic/simplewificfg.asp";

// Get initial token
        $client = new Client();
        $response = $client->request('GET', $gponRandUrl);
        $token = preg_replace('/^\xef\xbb\xbf/', '', $response->getBody()->getContents()); */

// Log in and get cookie
//         if (!empty($token)) {
//             $response = $client->request('POST', $gponLoginUrl, [
//                 'headers' => [
//                     'Cookie' => 'body:Language:english:id=-1',
//                 ],
//                 'form_params' => [
//                     'UserName' => $user,
//                     'PassWord' => $pass64,
//                     'x.X_HW_Token' => $token,
//                 ],
//             ]);
//            var_dump($response);
//            $extarer = '';
//             foreach ($response->getHeaders() as $name => $values) {
//                 echo $name . ": " . implode(", ", $values);
//                 if ($name = "Set-cookie") {
//                     $extarer = $values[0];
//                     break;
//                 }
//             }
//             $parts = explode('=', $extarer);
//             echo "parte: ". $parts[2]."final";
//             var_dump($parts);
// // obtener el valor de la cookie sid
//             if (count($parts) >= 2 && $parts[0] == 'Cookie') {
//                 $cookie_sid = $parts[2];
//                 $parts = explode('=', $extarer);
//                 echo "Valor de la cookie sid: " . $cookie_sid . "\n";
//             } else {
//                 echo "Error: la cadena no tiene el formato esperado.\n";
//             }
//
//             echo "extraer: ".$extarer;
// //            preg_match_all('/^Set-Cookie:\s*([^;]*)/mi', $extarer, $matches);
// //            $cookies = array();
// //            foreach($matches[1] as $item) {
// //                parse_str($item, $cookie);
// //                $cookies = array_merge($cookies, $cookie);
// //            }
//             $cookie = $parts[2]."=1";
//             echo "--------------------".$cookie."-------------------";
//         } else {
//             echo "Error: missing initial token\n";
//             exit(1);
//         }
        // get hw token
//        if (!empty($cookie)) {
//            $ch = curl_init();
//            curl_setopt($ch, CURLOPT_URL, $GPON_WLAN);
//            curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
//            curl_setopt($ch, CURLOPT_COOKIE, $cookie);
//            $response = curl_exec($ch);
//            $hwToken = '';
//            //echo "sitenia". $response;
//            echo substr($response, 1);
//            preg_match('/name="hwonttoken" value="([^"]+)"/', $response, $matches);
//            if (!empty($matches[1])) {
//                $hwToken = $matches[1];
//            }
//            curl_close($ch);
//            echo "-------".$hwToken;
//            if (str_contains($response, "hwonttoken")){
//                echo "sitenia";
//            }
//            if (!empty($hwToken)) {
//                $ch = curl_init();
//                curl_setopt($ch, CURLOPT_URL, $GPON_SET_WLAN);
//                curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
//                curl_setopt($ch, CURLOPT_COOKIE, $cookie);
//                curl_setopt($ch, CURLOPT_HTTPHEADER, array(
//                    'Upgrade-Insecure-Requests: 1',
//                    'Content-Type: application/x-www-form-urlencoded'
//                ));
//                curl_setopt($ch, CURLOPT_POST, true);
//                curl_setopt($ch, CURLOPT_POSTFIELDS, http_build_query(array(
//                    'y.SSID' => $SSID,
//                    'w.SSID' => $SSID,
//                    'k.PreSharedKey' => $PASSWLAN,
//                    'w.Key' => $PASSWLAN,
//                    'x.X_HW_Token' => $hwToken
//                )));
//                $response = curl_exec($ch);
//                curl_close($ch);
//            }
//        } else {
//            echo "Error: missing session cookie" ;
//        }

 //Get hw token
//         if (!empty($cookie)) {
//
//
//             $response = $client->request('GET', $gponWlan, [
//                 'headers' => [
//                     'Accept' => 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7',
//                     'Accept-Language' => 'es-419,es;q=0.9,es-ES;q=0.8,en;q=0.7,en-GB;q=0.6,en-US;q=0.5',
//                     'Connection' => 'keep-alive',
//                     'Cookie' => 'sid='.$cookie,
//                     'Referer' => 'http://192.168.18.1/index.asp',
//                     'Upgrade-Insecure-Requests' => '1',
//                 ],
//                 'verify' => false,
//                 'http_errors' => false
//             ]);
//
//             $body = (string) $response->getBody();
//             preg_match('/"hwonttoken" value="([0-9a-f]+)"/', $body, $matches);
//
//             $hwToken = $matches[1];
//
//             $response = $client->request('POST', $gponUrl.'/html/amp/wlanbasic/set.cgi', [
//                 'headers' => [
//                     'Cookie' => 'sid='.$cookie,
//                 ],
//                 'form_params' => [
//                     'w' => 'InternetGatewayDevice.X_HW_DEBUG.AMP.WifiCoverSetWlanBasic',
//                     'y' => 'InternetGatewayDevice.LANDevice.1.WLANConfiguration.1',
//                     'z' => 'InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.WPS',
//                     'k' => 'InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.PreSharedKey.1',
//                     'RequestFile' => 'html/amp/wlanbasic/WlanBasic.asp',
//                     'x.X_HW_Token' => $hwToken,
//                     'w.SSID' => $ssid,
//                     'y.SSID' => $ssid,
//                     'w.Key' => $passwlan,
//                     'k.PreSharedKey' => $passwlan,
// ]
//                 ]);
//         } else {
//             echo "cookie is missing";
//         }

//        $USER = 'Epadmin';
//        $PASSWLAN = '123456789';
//        $PASS = 'adminEp';
//        $SSID = 'HUAWEI1';
//        $IP = '192.168.18.1';
//
//        $PASS64 = base64_encode($PASS);
//
//        $GPON_URL = "http://${IP}";
//        $GPON_RAND_URL = "${GPON_URL}/asp/GetRandCount.asp";
//        $GPON_LOGIN_URL = "${GPON_URL}/login.cgi";
//        $GPON_RESET_URL = "${GPON_URL}/html/ssmp/reset/reset.asp";
//        $GPON_WLAN = "${GPON_URL}/html/amp/wlanbasic/WlanBasic.asp";
//        $GPON_WLAN_1 = "${GPON_URL}/html/amp/common/getSsidProcFlag.asp?&1=1";
//        $GPON_REBOOT_URL = "${GPON_URL}/html/ssmp/reset/set.cgi?x=InternetGatewayDevice.X_HW_DEBUG.SMP.DM.ResetBoard&RequestFile=html/ssmp/reset/reset.asp";
//        $GPON_SET_WLAN = "${GPON_URL}/html/amp/wlanbasic/set.cgi?w=InternetGatewayDevice.X_HW_DEBUG.AMP.WifiCoverSetWlanBasic&y=InternetGatewayDevice.LANDevice.1.WLANConfiguration.1&z=InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.WPS&k=InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.PreSharedKey.1&RequestFile=html/amp/wlanbasic/WlanBasic.asp";
//
//// get initial token
//        $ch = curl_init();
//        curl_setopt($ch, CURLOPT_URL, $GPON_RAND_URL);
//        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
//        $TOKEN = curl_exec($ch);
//        $TOKEN = preg_replace('/^\xef\xbb\xbf/', '', $TOKEN);
//        curl_close($ch);
//
//        var_dump($TOKEN);
//// log in and get cookie
//        // log in and get cookie
//        if (!empty($TOKEN)) {
//            $data = array(
//                'UserName' => $USER,
//                'PassWord' => $PASS64,
//                'x.X_HW_Token' => $TOKEN
//            );
//            $options = array(
//                'method' => 'POST',
//                'header' => 'Cookie: body:Language:english:id=-1',
//                'content' => http_build_query($data),
//                'ignore_errors' => true
//            );
//            $context = stream_context_create(array('http' => $options));
//            $response = file_get_contents($GPON_LOGIN_URL, false, $context);
//            preg_match('/Cookie\s+sid=([^\s;]+)/', $response[6], $matches);
//            $COOKIE = $matches[1] ?? '';
//            if (empty($COOKIE)) {
//                echo "Error: missing session cookie";
//                exit(1);
//            }
//        } else {
//            echo "Error: missing initial token";
//            exit(1);
//        }
//
//// get hw token
//        if (!empty($cookie)) {
//            $ch = curl_init();
//            curl_setopt($ch, CURLOPT_URL, $GPON_WLAN);
//            curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
//            curl_setopt($ch, CURLOPT_COOKIE, $cookie);
//            $response = curl_exec($ch);
//            $hwToken = '';
//            preg_match('/name="hwonttoken" value="([^"]+)"/', $response, $matches);
//            if (!empty($matches[1])) {
//                $hwToken = $matches[1];
//            }
//            curl_close($ch);
//            if (!empty($hwToken)) {
//                $ch = curl_init();
//                curl_setopt($ch, CURLOPT_URL, $GPON_SET_WLAN);
//                curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
//                curl_setopt($ch, CURLOPT_COOKIE, $cookie);
//                curl_setopt($ch, CURLOPT_HTTPHEADER, array(
//                    'Upgrade-Insecure-Requests: 1',
//                    'Content-Type: application/x-www-form-urlencoded'
//                ));
//                curl_setopt($ch, CURLOPT_POST, true);
//                curl_setopt($ch, CURLOPT_POSTFIELDS, http_build_query(array(
//                    'y.SSID' => $SSID,
//                    'w.SSID' => $SSID,
//                    'k.PreSharedKey' => $PASSWLAN,
//                    'w.Key' => $PASSWLAN,
//                    'x.X_HW_Token' => $hwToken
//                )));
//                $response = curl_exec($ch);
//                curl_close($ch);
//            }
//        } else {
//            echo "Error: missing session cookie" ;
//        }
//
//        var_dump($cookie);
        return view('home.index');
    }
}
