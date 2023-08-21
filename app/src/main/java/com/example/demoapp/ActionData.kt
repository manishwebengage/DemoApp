package com.example.demoapp

import com.google.gson.annotations.SerializedName


data class ActionData (

  @SerializedName("actions" ) var actions : ArrayList<Actions> = arrayListOf()

)