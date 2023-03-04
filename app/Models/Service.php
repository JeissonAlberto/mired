<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Service extends Model
{
    use HasFactory;

    protected $fillable = [
        'tipo_de_tecnologia',
        'precio',
        'velocidad',
        'direccion_ip',
        'sn',
        'marca',
        'referencia',
        'direccion_mac',
        'password_ssid',
        'ssid',
        'user_id',
    ];

    public function user(){
        return $this->belongsTo('App\Models\User');
    }
}
