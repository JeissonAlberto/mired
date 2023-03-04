@extends('layouts.app-master')

@section('content')

    <div class="bg-light p-5 rounded">
        <h1>Información del Usuario</h1>
        <div class="card-body">
            <p><strong> Nombre:</strong> {{$user->name}}</p>
            <p>Cédula: {{$user->cedula}}</p>
            <p>Dirección: {{$user->direccion}}</p>
            <p>Correo: {{$user->email}}</p>
            <p>Teléfono: {{$user->telefono}}</p>

        </div>
    </div>

    <div class="card">
        <div class="card-header"> <h2>Lista de Servicios</h2> <a class="btn btn-primary" href="{{route('service.create', $user->id)}}">Crear</a></div>
        <div class="card-group" >
        @foreach($user->services as $service )
        <div class="card-body">
            <p> <strong>Tipo de Tecnologia: </strong>  {{$service->tipo_de_tecnologia}}</p>
            <p> <strong>s/n: </strong>  {{$service->sn}}</p>
            <p> <strong>Marca: </strong>  {{$service->marca}}</p>
            <p> <strong>Referencia: </strong>  {{$service->referencia}}</p>
            <p> <strong>Direccion mac: </strong>  {{$service->direccion_mac}}</p>
            <p> <strong>ssid: </strong>  {{$service->ssid}}</p>
            <p> <strong>Direccion ip: </strong>  {{$service->direccion_ip}}</p>
            <p> <strong>Precio: </strong>  {{$service->precio}}</p>
            <div class="row-cols-auto">
                <a class="btn btn-primary" href="{{route('service.edit',$service->id)}}">Editar</a>

                <form method="post" action="{{ url('/services/'.$service->id) }}">
                    {{csrf_field()}}
                    {{ method_field('DELETE') }}
                    <button class="btn btn-danger" type="submit" onclick="return confirm('¿Borrar?');" >Borrar</button>
                </form>
            </div>

        </div>
        @endforeach
        </div>
    </div>
@endsection
