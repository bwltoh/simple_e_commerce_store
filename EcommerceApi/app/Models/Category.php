<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Category extends Model
{
    use HasFactory;

    protected $table="categories";



 protected $fillable = [
        'name',
        'description',
        
    ];

    /**
     * The attributes that should be hidden for arrays.
     *
     * @var array
     */
    protected $hidden = [
        'created_at',
        'updated_at',
    ];
 
    public function rel_products(){
    	return $this->hasMany('App\Models\Product','category_id','id');
    }

    
}
