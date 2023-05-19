<?php

namespace App\Http\Controllers;

use App\Models\Service;
use App\Http\Requests\StoreServiceRequest;
use App\Http\Requests\UpdateServiceRequest;
use App\Models\User;
use http\Env\Request;
use Illuminate\Http\RedirectResponse;
use Illuminate\Http\Response;

class ServiceController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index(): Response
    {
        //
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create(User $user)
    {
//        var_dump($user);
//        Service::create([
//            "tipo_de_tecnologia"=> "njknj",
//            "marca"=>" jhjknj",
//            "referencia"=>" dncjksnskjdn",
//            "sn"=> "hckvfhvf",
//            "direccion_mac"=> "kdjncdskcds",
//            "direccion_ip"=> "kdjncdskcds",
//            "ssid"=> "kncjnskjnsfjks",
//            "password_ssid"=> "kncjnskjnsfjks",
//            "precio"=> 7874,
//            "velocidad"=> "nhjbhjbjh",
//            "user_id"=>"nhjnhj",
//]);
        return view("services.create", compact(['user']));
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store( StoreServiceRequest $request) :RedirectResponse
    {
        $request->validated();
        $service = Service::create([
            "tipo_de_tecnologia"=> $request["tipo_de_tecnologia"],
            "marca"=> $request["marca"],
            "referencia"=> $request["referencia"],
            "sn"=> $request["sn"],
            "direccion_mac"=> $request["direccion_mac"],
            "direccion_ip"=> $request["direccion_ip"],
            "ssid"=> $request["ssid"],
            "password_ssid"=> $request["password_ssid"],
            "precio"=> $request["precio"],
            "velocidad"=> $request["velocidad"],
            "user_id"=> $request["user_id"],
        ]);
        $user = $service->user;
        return redirect('/users/'.$user->id)->with('success', "Servicio creado");
    }

    /**
     * Display the specified resource.
     */
    public function show(Service $service): Response
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Service $service)
    {
        $user = $service->user;
        return view('services.edit',compact('service', 'user'));
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(UpdateServiceRequest $request, Service $service): RedirectResponse
    {
        $request->validated();
        $service->update([
            "tipo_de_tecnologia"=> $request["tipo_de_tecnologia"],
            "marca"=> $request["marca"],
            "referencia"=> $request["referencia"],
            "sn"=> $request["sn"],
            "direccion_mac"=> $request["direccion_mac"],
            "direccion_ip"=> $request["direccion_ip"],
            "ssid"=> $request["ssid"],
            "password_ssid"=> $request["password_ssid"],
            "precio"=> $request["precio"],
            "velocidad"=> $request["velocidad"],
            "user_id"=> $request["user_id"],
        ]);
        $user = $service->user;

        return redirect('/users/'.$user->id)->with('success', "Servicio actualizado");
    }

    public function updatePassword(Service $service, UpdateServiceRequest $request)
    {
    //$service = Service::where('id',  $request["id"])->first();
        $service->update([
            "ssid"=> $request["ssid"],
            "password_ssid"=> $request["password"],
        ]);
        if ($service->id != ""){
            return response()->json("",200);
        } else {
            return response()->json(['message' => 'Servicio no encontrado'], 404);
        }
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Service $service): RedirectResponse
    {
        Service::destroy($service->id);
        return redirect ('/users/'.$service->user->id);
    }
}
