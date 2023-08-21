package com.example.demoapp

import com.google.gson.annotations.SerializedName


data class Actions (

  @SerializedName("actionText") var actionText     : String?  = null,
  @SerializedName("sdkId") var sdkId          : Int?     = null,
  @SerializedName("actionCategory") var actionCategory : String?  = null,
  @SerializedName("actionEId"      ) var actionEId      : String?  = null,
  @SerializedName("actionTarget"   ) var actionTarget   : String?  = null,
  @SerializedName("type"           ) var type           : String?  = null,
  @SerializedName("actionLink"     ) var actionLink     : String?  = null,
  @SerializedName("isPrime"        ) var isPrime        : Boolean? = null

)