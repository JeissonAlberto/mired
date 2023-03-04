<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class StoreServiceRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     */
    public function authorize(): bool
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array<string, \Illuminate\Contracts\Validation\Rule|array|string>
     */
    public function rules(): array
    {
        return [
            'tipo_de_tecnologia' => 'required',
            'marca' => 'required',
            'referencia' => 'required',
            'sn' => 'required',
            'direccion_mac' => 'required',
            'direccion_ip' => 'required',
            'ssid' => 'required',
            'password_ssid' => 'required',
            'precio' => 'required',
            'velocidad' => 'required',
            'user_id' => 'required',
        ];
    }
}
