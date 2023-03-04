<div class="form-group form-floating mb-3">
    <input type="email" class="form-control" name="email" value="@isset($user->email) {{$user->email}} @endisset" placeholder="name@example.com" required="required" autofocus>
    <label for="floatingEmail">Correo electronico</label>
    @if ($errors->has('email'))
        <span class="text-danger text-left">{{ $errors->first('email') }}</span>
    @endif
</div>



<div class="form-group form-floating mb-3">
    <input type="text" class="form-control" name="name" value="@isset($user->name){{$user->name}} @endisset"  placeholder="name" required="required" autofocus>
    <label for="floatingName">Nombre completo</label>
    @if ($errors->has('name'))
        <span class="text-danger text-left">{{ $errors->first('name') }}</span>
    @endif
</div>

<div class="form-group form-floating mb-3">
    <input type="text" class="form-control" name="direccion" value="@isset($user->direccion){{$user->direccion}} @endisset" placeholder="direccion" required="required" autofocus>
    <label for="floatingName">Dirección</label>
    @if ($errors->has('direccion'))
        <span class="text-danger text-left">{{ $errors->first('direccion') }}</span>
    @endif
</div>

<div class="form-group form-floating mb-3">
    <input type="text" class="form-control" name="cedula" value="@isset($user->cedula){{$user->cedula}} @endisset" placeholder="cedula" required="required" autofocus>
    <label for="floatingName">Cédula</label>
    @if ($errors->has('cedula'))
        <span class="text-danger text-left">{{ $errors->first('cedula') }}</span>
    @endif
</div>

<div class="form-group form-floating mb-3">
    <input type="number" class="form-control" name="telefono" value="@isset($user->telefono){{$user->telefono}} @endisset" placeholder="telefono" required="required" autofocus>
    <label for="floatingName">Telefono</label>
    @if ($errors->has('telefono'))
        <span class="text-danger text-left">{{ $errors->first('telefono') }}</span>
    @endif
</div>


<div class="form-group form-floating mb-3">
    <input type="password" class="form-control" name="password" value="" placeholder="Password">
    <label for="floatingPassword">Contraseña</label>
    @if ($errors->has('password'))
        <span class="text-danger text-left">{{ $errors->first('password') }}</span>
    @endif
</div>

@if ($errors)
    <span class="text-danger text-left">{{ $errors }}</span>
@endif

<button class="w-100 btn btn-lg btn-primary" type="submit">Guardar</button>
