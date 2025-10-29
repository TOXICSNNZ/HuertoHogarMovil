package com.example.huertohogarmovil.data.repository

import com.example.huertohogarmovil.R
import com.example.huertohogarmovil.model.Product

class CatalogRepository {
    val products = listOf(
        Product("FR001","Manzanas Fuji",1200,150,"kilo","Manzanas Fuji crujientes y dulces, del Valle del Maule.","Frutas Frescas",imageResId = R.drawable.manzanas),
        Product("FR002","Naranjas Valencia",1000,200,"kilo","Jugosas e ideales para jugos.","Frutas Frescas",imageResId = R.drawable.naranjas),
        Product("FR003","Plátanos Cavendish",800,250,"kilo","Ricos en potasio.","Frutas Frescas",imageResId = R.drawable.bananas),
        Product("VR001","Zanahorias Orgánicas",900,100,"kilo","Sin pesticidas; fuente de vitamina A.","Verduras Orgánicas",imageResId = R.drawable.zanahorias),
        Product("VR002","Espinacas Frescas",700,80,"bolsa 500g","Frescas y nutritivas.","Verduras Orgánicas",imageResId = R.drawable.espinacas),
        Product("VR003","Pimientos Tricolores",1500,120,"kilo","Color y antioxidantes.","Verduras Orgánicas",imageResId = R.drawable.pimientos),
        Product("PO001","Miel Orgánica",5000,50,"frasco 500g","Miel pura local.","Productos Orgánicos",imageResId = R.drawable.miel),
        Product("PO003","Quinua Orgánica",3000,60,"kilo","Alta en proteína.","Productos Orgánicos",imageResId = R.drawable.quinua),
        Product("PL001","Leche Entera",1200,90,"litro","Leche entera fresca.","Productos Lácteos",imageResId = R.drawable.leche),
    )
}
