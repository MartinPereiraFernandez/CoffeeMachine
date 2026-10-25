object CoffeeMachine{
    var currentState: CoffeeMachineState = CoffeeMachineState.Idle
    var pago = 0.6
    enum class tiposCafe {Solo, ConLeche, Capuchino}
    var tipoCafe = tiposCafe.entries[0]
    var Azucar = 0
    val cantidadMaximaAzucar = 10
    val cantidadMinimaAzucar = 0


    fun gestorEstados(){
        when (currentState) {
            is CoffeeMachineState.Idle ->{
                println("Estado actual: "+currentState)
                println("Cantidad en la cartera: "+pago)
                println("Precio del cafe : 0.6€")
                if (pago >= 0.6){
                    println("Pago finalizado.")
                    currentState=CoffeeMachineState.seleccionTipoCafe
                } else {
                    println("Pago insuficiente...")
                    currentState= CoffeeMachineState.error
                }
            }

            is CoffeeMachineState.seleccionTipoCafe->{
                if (tipoCafe in tiposCafe.entries){
                    println("Estado actual: "+currentState)
                    println("Seleccionado tipo cafe: "+tipoCafe)
                    currentState = CoffeeMachineState.cantidadAzucar
                } else {
                    println("Tipo de cafe no disponible dentro de las posibilidades")
                    currentState = CoffeeMachineState.error
                }
            }

            is CoffeeMachineState.cantidadAzucar-> {
                println("Estado actual: "+currentState)
                aumentarCantidadAzucar(5)
                disminuirCantidadAzucar(3)
                currentState = CoffeeMachineState.sirviendoCafe
            }

            is CoffeeMachineState.sirviendoCafe->{
                println("Estado actual: "+currentState)
                Thread.sleep(5000)
                println("Sirviendo cafe...")
                currentState = CoffeeMachineState.recogerCafe
            }

            is CoffeeMachineState.recogerCafe->{
                println("Estado actual: "+currentState)
                println("Ya puede recoger su cafe")
            }

            is CoffeeMachineState.error -> {
                println("Estado actual: "+currentState)
                println("Devolviendo dinero...")
                currentState=CoffeeMachineState.Idle
            }

        }
    }
    fun aumentarCantidadAzucar(cantidad : Int){
        if (cantidad >= 0){
            if (Azucar <= cantidadMaximaAzucar && cantidad <=cantidadMaximaAzucar){
                Azucar+=cantidad
                println("Aumentada cantidad de azucar en : "+cantidad)
            } else {
                println("Cantidad maxima de azucar superada...")
                currentState= CoffeeMachineState.error
            }
        } else {
            println("Pon una cantidad positiva de azucar")
            currentState= CoffeeMachineState.error
        }
    }

    fun disminuirCantidadAzucar(cantidad : Int){
        if (cantidad >= 0){
            if (Azucar >= cantidadMinimaAzucar && cantidad >=cantidadMinimaAzucar){
                Azucar-=cantidad
                println("Disminuida cantidad de azucar en "+cantidad)
            } else {
                println("Cantidad minima de azucar no superada...")
                currentState= CoffeeMachineState.error
            }
        } else {
            println("Pon una cantidad positiva de azucar")
            currentState= CoffeeMachineState.error
        }
    }

}