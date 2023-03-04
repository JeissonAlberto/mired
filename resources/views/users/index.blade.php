
@extends('layouts.app-master')

@section('content')
    <div class="bg-light p-5 rounded">

            <h1>Gestión de usuarios</h1>

        <table class="table table-dark table-striped">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Nombre</th>
                <th scope="col">Cédula</th>
                <th scope="col">Telefono</th>
                <th scope="col">Correo</th>

                <th scope="col">Acciones</th>
                <th scope="col"></th>

                <th scope="col">
                    <a class="btn btn-primary" href="{{route('user.create')}}">Crear</a>
                </th>

            </tr>
            </thead>
            <tbody>
            @foreach($users as $user)
            <tr>
                <th >{{$user->id}}</th>
                <td>{{$user->name}}</td>
                <td>{{$user->cedula}}</td>
                <td>{{$user->telefono}}</td>
                <td>{{$user->email}}</td>

                <td>

                 <a class="btn btn-primary" href="{{route('user.show',$user->id)}}">Info</a>
                </td>
                <td>

                 <a class="btn btn-primary" href="{{route('user.edit',$user->id)}}">Editar</a>
                </td>

                <td>
                <form method="post" action="{{ url('/users/'.$user->id) }}">
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
