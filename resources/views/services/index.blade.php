
@extends('layouts.app-master')

@section('content')
    <div class="bg-light p-5 rounded">

            <h1>Informacion del servicio</h1>

        <table class="table table-dark table-striped">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">tipo_de_tecnologia</th>
                <th scope="col">velocidad</th>
                <th scope="col">precio</th>
                <th scope="col">modem_id</th>

                <th scope="col">Acciones</th>
                <th scope="col"></th>

                <th scope="col">
                    <a class="btn btn-primary" href="{{route('user.create')}}">Crear</a>
                </th>

            </tr>
            </thead>
            <tbody>
            @foreach($services as $service)
            <tr>
                <th >{{$service->id}}</th>
                <td>{{$service->tipo_de_tecnologia }}</td>
                <td>{{$service->velocidad}}</td>
                <td>{{$service->precio}}</td>
                <td>{{$service->modem_id}}</td>

                <td>

                 <a class="btn btn-primary" href="{{route('$service.show',$service->id)}}">Info</a>
                </td>
                <td>

                 <a class="btn btn-primary" href="{{route('$service.edit',$service->id)}}">Editar</a>
                </td>

                <td>
                <form method="post" action="{{ url('$/service/'.$service->id) }}">
                {{csrf_field()}}
                {{ method_field('DELETE') }}
                <button class="btn btn-danger" type="submit" onclick="return confirm('¿Borrar?');" >Borrar</button>
                </form>
                </td>


            </tr>
            @endforeach
            </tbody>
        </table>
    </div>
@endsection
