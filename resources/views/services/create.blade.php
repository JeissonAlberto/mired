@extends('layouts.app-master')

@section('content')

    <div class="bg-light p-5 rounded">
        <h1>Crear servicio</h1>
        <div class="card-body">
            <form method="POST" action="{{route('service.store')}}">
                @csrf
                @include('/services/partials/form')
                <a class="btn btn-danger" href="{{route('user.show', $user->id)}}">Cancelar</a>
            </form>
        </div>
    </div>
@endsection
