<?php

namespace App\Http\Controllers;

use App\Models\Modem;
use App\Http\Requests\StoreModemRequest;
use App\Http\Requests\UpdateModemRequest;
use Illuminate\Http\RedirectResponse;
use Illuminate\Http\Response;

class ModemController extends Controller
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
    public function create(): Response
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(StoreModemRequest $request): RedirectResponse
    {
        //
    }

    /**
     * Display the specified resource.
     */
    public function show(Modem $modem): Response
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Modem $modem): Response
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(UpdateModemRequest $request, Modem $modem): RedirectResponse
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Modem $modem): RedirectResponse
    {
        //
    }
}
