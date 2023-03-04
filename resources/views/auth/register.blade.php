@extends('layouts.auth-master')

@section('content')
    <form method="post" action="{{ route('register.perform') }}">

        <input type="hidden" name="_token" value="{{ csrf_token() }}" />
        <img class="mb-4" src="{!! url('images/bootstrap-logo.svg') !!}" alt="" width="72" height="57">

        <h1 class="h3 mb-3 fw-normal">Registrarse</h1>

        <div class="form-group form-floating mb-3">
            <input type="email" class="form-control" name="email" value="{{ old('email') }}" placeholder="name@example.com" required="required" autofocus>
            <label for="floatingEmail">Correo electronico</label>
            @if ($errors->has('email'))
                <span class="text-danger text-left">{{ $errors->first('email') }}</span>
            @endif
        </div>



        <div class="form-group form-floating mb-3">
                    <input type="text" class="form-control" name="name" value="{{ old('name') }}" placeholder="name" required="required" autofocus>
                    <label for="floatingName">Nombre completo</label>
                    @if ($errors->has('name'))
                        <span class="text-danger text-left">{{ $errors->first('name') }}</span>
                    @endif
       </div>

       <div class="form-group form-floating mb-3">
                            <input type="text" class="form-control" name="direccion" value="{{ old('direccion') }}" placeholder="direccion" required="required" autofocus>
                            <label for="floatingName">Dirección</label>
                            @if ($errors->has('direccion'))
                                <span class="text-danger text-left">{{ $errors->first('direccion') }}</span>
                            @endif
       </div>

      <div class="form-group form-floating mb-3">
                                    <input type="text" class="form-control" name="cedula" value="{{ old('cedula') }}" placeholder="cedula" required="required" autofocus>
                                    <label for="floatingName">Cédula</label>
                                    @if ($errors->has('cedula'))
                                        <span class="text-danger text-left">{{ $errors->first('cedula') }}</span>
                                    @endif
       </div>

       <div class="form-group form-floating mb-3">
         <input type="number" class="form-control" name="telefono" value="{{ old('telefono') }}" placeholder="telefono" required="required" autofocus>
           <label for="floatingName">Telefono</label>
              @if ($errors->has('telefono'))
                <span class="text-danger text-left">{{ $errors->first('telefono') }}</span>
                 @endif
        </div>


        <div class="form-group form-floating mb-3">
            <input type="password" class="form-control" name="password" value="{{ old('password') }}" placeholder="Password" required="required">
            <label for="floatingPassword">Contraseña</label>
            @if ($errors->has('password'))
                <span class="text-danger text-left">{{ $errors->first('password') }}</span>
            @endif
        </div>

        <div class="form-group form-floating mb-3">
            <input type="password" class="form-control" name="password_confirmation" value="{{ old('password_confirmation') }}" placeholder="Confirm Password" required="required">
            <label for="floatingConfirmPassword">Confirmar contraseña</label>
            @if ($errors->has('password_confirmation'))
                <span class="text-danger text-left">{{ $errors->first('password_confirmation') }}</span>
            @endif
        </div>

        <button class="w-100 btn btn-lg btn-primary" type="submit">Registrarse</button>

        @include('auth.partials.copy')
    </form>
@endsection
