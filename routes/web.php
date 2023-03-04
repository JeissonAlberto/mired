<?php

use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::group(['namespace' => 'App\Http\Controllers'], function()
{
    /**
     * Home Routes
     */
    Route::get('/', 'HomeController@index')->name('home.index');

    Route::group(['middleware' => ['guest']], function() {
        /**
         * Register Routes
         */
        Route::get('/register', 'RegisterController@show')->name('register.show');
        Route::post('/register', 'RegisterController@register')->name('register.perform');

        /**
         * Login Routes
         */
        Route::get('/login', 'LoginController@show')->name('login.show');
        Route::post('/login', 'LoginController@login')->name('login.perform');

    });

    Route::group(['middleware' => ['auth']], function() {
        /**
         * Logout Routes
         */
        Route::get('/logout', 'LogoutController@perform')->name('logout.perform');

        //Rutas del usuario
        Route::get('/users', 'UserController@index')->name('user.index');
        Route::delete('/users/{user}', 'UserController@destroy')->name('user.destroy');
        Route::get('/users/create', 'UserController@create')->name('user.create');
        Route::get('/users/{user}', 'UserController@show')->name('user.show');
        Route::post('/users/store', 'UserController@store')->name('user.store');
        Route::get('/users/{user}/edit', 'UserController@edit')->name('user.edit');
        Route::put('/users/{user}', 'UserController@update')->name('user.update');

        //Rutas del servicio
        Route::get('/services', 'ServiceController@index')->name('service.index');
        Route::delete('/services/{service}', 'ServiceController@destroy')->name('service.destroy');
        Route::get('/users/{user}/services/create', 'ServiceController@create')->name('service.create');
        Route::get('/services/{service}', 'ServiceController@show')->name('service.show');
        Route::post('/services/store', 'ServiceController@store')->name('service.store');
        Route::get('/services/{service}/edit', 'ServiceController@edit')->name('service.edit');
        Route::put('/services/{service}', 'ServiceController@update')->name('service.update');

        //Rutas del modem
    });
});
