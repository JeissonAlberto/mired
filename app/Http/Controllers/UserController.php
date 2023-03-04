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
        $RESULT = shell_exec("gpon_reboot.sh");
        var_dump($RESULT);
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
