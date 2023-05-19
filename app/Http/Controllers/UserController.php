<?php

namespace App\Http\Controllers;

use App\Http\Requests\UserRequest;
use App\Http\Requests\UserUpdateRequest;
use App\Models\User;
use Illuminate\Http\RedirectResponse;
use Illuminate\Http\Request;
use Illuminate\Http\Response;

class UserController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $users = User::all();


        return view("users.index", compact(['users']));
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        return view("users.create");
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(UserRequest $request): RedirectResponse
    {
        $user = User::create($request->validated());



        return redirect('/users')->with('success', "Usuario creado");
    }

    /**
     * Display the specified resource.
     */
    public function show(User $user)
    {
        return view('users.show',compact('user'));

    }
    public function getUser(string $email)
    {
            $user = User::where('email', $email)->first();
            if ($user->id != ""){
            return response()->json($user, 200);
        } else {
            return response()->json(['message' => 'Invalid data'], 404);
        }
    }

    public function getUserServices(string $email)
    {
            $user = User::where('email', $email)->first();
            if ($user->id != ""){
            return response()->json($user->services[0], 200);
        } else {
            return response()->json(['message' => 'Invalid data'], 404);
        }
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(User $user)
    {

        return view('users.edit',compact('user'));

    }

    /**
     * Update the specified resource in storage.
     */
    public function update(UserUpdateRequest $request, User $user): RedirectResponse
    {
        $request->validated();
        $user->update([
            "cedula"=> $request["cedula"],
            "name"=> $request["name"],
            "telefono"=> $request["telefono"],
            "direccion"=> $request["direccion"],
            "password"=> $request["password"],
        ]);
        return redirect('/users')->with('success', "Usuario actualizado");
    }
    public function updateUser(User $user,UserUpdateRequest $request): \Illuminate\Http\JsonResponse
    {

        $request->validated();
        //$user = User::where('email',  $request["email"])->first();
        $user->update([
            "cedula"=> $request["cedula"],
            "name"=> $request["name"],
            "telefono"=> $request["telefono"],
            "direccion"=> $request["direccion"],
            "password"=> $request["password"],
        ]);
        if ($user->id != ""){
            return response()->json("",200);
        } else {
            return response()->json(['message' => 'Usuario no encontrado'], 404);
        }
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy( string $id) : RedirectResponse
    {
        //
       User::destroy($id);
        return redirect ('users');
    }
}
