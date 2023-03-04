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
        Schema::create('modems', function (Blueprint $table) {
            $table->id();
            $table->timestamps();
            $table->string("direccion_ip");
            $table->string("s/n");
            $table->string("marca");
            $table->string("referencia");
            $table->string("direccion_mac");
            $table->string("password_ssid");
            $table->string("ssid");
            $table->string("diagnostico_red");
            $table->foreignId("service_id")->references("id")->on("services");
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('modems');
    }
};
