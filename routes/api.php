<?php

use App\Http\Controllers\ServiceController;
use Illuminate\Http\Request;
use App\Http\Controllers\UserController;
use App\Http\Controllers\AuthController;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "api" middleware group. Make something great!
|
*/

Route::post('/login', [AuthController::class, 'login']);

Route::middleware('auth:sanctum')->group(function () {
    Route::get('/user/{email}', [UserController::class, 'getUser']);
    Route::get('/servicesByUserEmail/{email}', [UserController::class, 'getUserServices']);
    Route::put('/userUpdate/{user}', [UserController::class, 'updateUser']);
    Route::put('/updatePassword/{service}', [ServiceController::class, 'updatePassword']);
});
