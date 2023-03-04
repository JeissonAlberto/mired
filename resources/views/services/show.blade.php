@extends('layouts.app-master')

@section('content')

    <div class="bg-light p-5 rounded">
        <h1>Información del servicio</h1>
        <div class="card-body">
            <p>tipo_de_tecnologia: {{$user->tipo_de_tecnologia}}</p>
            <p>precio: {{$user->velocidad}}</p>
            <p>velocidad: {{$user->precio}}</p>
            <p>modem_id: {{$user->modem_id}}</p>


        </div>
    </div>
@endsection
