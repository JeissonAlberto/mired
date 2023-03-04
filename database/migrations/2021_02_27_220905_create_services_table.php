<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('services', function (Blueprint $table) {
            $table->id();
            $table->timestamps();
            $table->string("tipo_de_tecnologia");
            $table->bigInteger("precio");
            $table->string("velocidad");
            $table->string("direccion_ip");
            $table->string("sn");
            $table->string("marca");
            $table->string("referencia");
            $table->string("direccion_mac");
            $table->string("password_ssid");
            $table->string("ssid");
            $table->foreignId('user_id')->references('id')->on('users');

        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('services');
    }
};
