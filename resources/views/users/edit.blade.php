@extends('layouts.app-master')

@section('content')

    <div class="bg-light p-5 rounded">
        <h1>Editar Usuario</h1>
        <div class="card-body">
            <form method="POST" action=" {{route('user.update', $user->id)}}">
                @method('PUT')
                @csrf
                @include('/users/partials/form')
                <a class="btn btn-danger" href="{{route('user.index')}}">Cancelar</a>
            </form>
        </div>
    </div>
@endsection
