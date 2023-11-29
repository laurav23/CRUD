package com.example.crud

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ProductsAdapter(
    var context: Context,
    var products: List<Products>
): RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {


    private var Onclick: OnItemClicked? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_product,parent,false)
        return ProductsViewHolder(vista)
    }

    override fun getItemCount(): Int {
        val itemCount = products.size
        Log.d("ItemCount", "El número de elementos en la lista es $itemCount")
        return itemCount
    }
    fun updateData(newProducts: List<Products>) {
      //  products.clear()
      //  products.addAll(newProducts)
        notifyDataSetChanged()
    }





    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = products[position]

        with(holder) {
            tvIdProducto.text = product.id.toString()
            tvSku.text = product.sku
            tvNombre.text = product.name
            tvdescription.text = product.description
            tvprice.text = product.price
            tvquantity.text = product.quantity
            tvstatus.text = product.status
        }

        holder.btnEditar.setOnClickListener {
            Onclick?.editarProduct(product)
        }

        holder.btnBorrar.setOnClickListener {
            Onclick?.borrarProduct(product.id)
        }

        holder.Agregar.setOnClickListener {
            mostrarDialogo(product)
        }

    }

    private fun mostrarDialogo(product: Products) {
        val builder = AlertDialog.Builder(context)
        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.model_perfil, null)
        builder.setView(dialogView)

        val editText1 = dialogView.findViewById<EditText>(R.id.name)
        val editText2 = dialogView.findViewById<EditText>(R.id.sku)
        val editText3 = dialogView.findViewById<EditText>(R.id.description)
        val editText4 = dialogView.findViewById<EditText>(R.id.price)
        val editText5 = dialogView.findViewById<EditText>(R.id.quantity)
        val editText6 = dialogView.findViewById<EditText>(R.id.status)

        // Establecer los datos del producto en los campos del diálogo
        editText1.setText(product.name)
        editText2.setText(product.sku)
        editText3.setText(product.description)
        editText4.setText(product.price)
        editText5.setText(product.quantity)
        editText6.setText(product.status)

        builder.setTitle("Editar Producto")
            .setPositiveButton("Aceptar") { dialog, which ->
                val input1 = editText1.text.toString()
                val input2 = editText2.text.toString()
                val input3 = editText3.text.toString()
                val input4 = editText4.text.toString()
                val input5 = editText5.text.toString()
                val input6 = editText6.text.toString()
                // Realizar la acción necesaria con los datos editados
                // Por ejemplo, puedes llamar a una función para actualizar el producto
                 //updateProduct(product.id, input1, input2)

               // updateProduct(TraerProduct(input1, input2, input3, input4, input5, input6), product.id.toString())

            }
            .setNegativeButton("Cancelar") { dialog, which ->
                // Acciones al cancelar la edición del producto
            }

        val alertDialog = builder.create()
        alertDialog.show()
    }

    inner class ProductsViewHolder(itemView: View): ViewHolder(itemView){

        val tvIdProducto = itemView.findViewById(R.id.tvIdProducto) as TextView
        val tvSku = itemView.findViewById(R.id.tvSku) as TextView
        val tvNombre = itemView.findViewById(R.id.tvNombre) as TextView
        val tvdescription = itemView.findViewById(R.id.tvdescription) as TextView
        val tvprice = itemView.findViewById(R.id.tvprice) as TextView
        val tvquantity = itemView.findViewById(R.id.tvquantity) as TextView
        val tvstatus = itemView.findViewById(R.id.tvstatus) as TextView

        val btnEditar = itemView.findViewById(R.id.btnEditar) as Button
        val btnBorrar = itemView.findViewById(R.id.btnBorrar) as Button
        val Agregar = itemView.findViewById(R.id.Agregar) as Button

    }
    interface  OnItemClicked{
        fun editarProduct(products: Products)
        fun borrarProduct(id: Int)
    }

    fun setOnClick(onClick: OnItemClicked?) {
        this.Onclick = onClick
    }




}