<div class="form-group form-floating mb-3">
    <select  class="form-control" name="tipo_de_tecnologia" >
        <option value="Fibra Optica">Fibra Optica</option>
        <option value="RadioEnlace">RadioEnlace</option>
    </select>
    <label for="tipo_de_tecnologia">Tipo de tecnologia</label>
    @if ($errors->has('tipo_de_tecnologia'))
        <span class="text-danger text-left">{{ $errors->first('tipo_de_tecnologia') }}</span>
    @endif
</div>



<div class="form-group form-floating mb-3">
    <input type="text" class="form-control" name="marca" value="@isset($service->marca){{$service->marca}} @endisset"  placeholder="name" required="required" autofocus>
    <label for="marca">Marca</label>
    @if ($errors->has('marca'))
        <span class="text-danger text-left">{{ $errors->first('marca') }}</span>
    @endif
</div>


<div class="form-group form-floating mb-3">
    <input type="text" class="form-control" name="referencia" value="@isset($service->referencia){{$service->referencia}} @endisset" placeholder="referencia" required="required" autofocus>
    <label for="referencia">Referencia</label>
    @if ($errors->has('referencia'))
        <span class="text-danger text-left">{{ $errors->first('referencia') }}</span>
    @endif
</div>

<div class="form-group form-floating mb-3">
    <input type="text" class="form-control" name="sn" value="@isset($service->sn){{$service->sn}} @endisset" placeholder="s/n" required="required" autofocus>
    <label for="sn">s/n</label>
    @if ($errors->has('sn'))
        <span class="text-danger text-left">{{ $errors->first('sn') }}</span>
    @endif
</div>


<div class="form-group form-floating mb-3">
    <input type="text" class="form-control" name="direccion_mac" value="@isset($service->direccion_mac){{$service->direccion_mac}} @endisset" placeholder="direccion_mac" required="required" autofocus>
    <label for="direccion_mac">Direccion mac</label>
    @if ($errors->has('direccion_mac'))
        <span class="text-danger text-left">{{ $errors->first('direccion_mac') }}</span>
    @endif
</div>

<div class="form-group form-floating mb-3">
    <input type="text" class="form-control" name="direccion_ip" value="@isset($service->direccion_ip){{$service->direccion_ip}} @endisset" placeholder="direccion_ip" required="required" autofocus>
    <label for="direccion_ip">Direccion ip</label>
    @if ($errors->has('direccion_ip'))
        <span class="text-danger text-left">{{ $errors->first('direccion_ip') }}</span>
    @endif
</div>


<div class="form-group form-floating mb-3">
    <input type="text" class="form-control" name="ssid" value="@isset($service->ssid){{$service->ssid}} @endisset" placeholder="ssid" required="required" autofocus>
    <label for="ssid">ssid</label>
    @if ($errors->has('ssid'))
        <span class="text-danger text-left">{{ $errors->first('ssid') }}</span>
    @endif

</div>

<div class="form-group form-floating mb-3">
    <input type="text" class="form-control" name="password_ssid" value="@isset($service->password_ssid){{$service->password_ssid}} @endisset" placeholder="password_ssid" required="required" autofocus>
    <label for="ssid">Contraseña ssid</label>
    @if ($errors->has('password_ssid'))
        <span class="text-danger text-left">{{ $errors->first('password_ssid') }}</span>
    @endif

</div>

<div class="form-group form-floating mb-3">
    <input type="number" class="form-control" name="precio" value="@isset($service->precio){{$service->precio}} @endisset" placeholder="precio" required="required" autofocus>
    <label for="ssid">Precio</label>
    @if ($errors->has('precio'))
        <span class="text-danger text-left">{{ $errors->first('precio') }}</span>
    @endif

</div>

<div class="form-group form-floating mb-3">
    <input type="text" class="form-control" name="velocidad" value="@isset($service->velocidad){{$service->velocidad}} @endisset" placeholder="velocidad" required="required" autofocus>
    <label for="velocidad">Velocidad</label>
    @if ($errors->has('velocidad'))
        <span class="text-danger text-left">{{ $errors->first('velocidad') }}</span>
    @endif

</div>

<div class="form-group form-floating mb-3">
    <input type="text" class="form-control" hidden name="user_id" value="@isset($user->id){{$user->id}} @endisset" placeholder="{{$user->name}}" required="required" autofocus>
    @if ($errors->has('user_id'))
        <span class="text-danger text-left">{{ $errors->first('user_id') }}</span>
    @endif
</div>


<button class="w-100 btn btn-lg btn-primary" type="submit">Guardar</button>
