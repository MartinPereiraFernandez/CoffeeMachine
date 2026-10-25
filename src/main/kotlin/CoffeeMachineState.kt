sealed class CoffeeMachineState{
    data object Idle: CoffeeMachineState();
    data object seleccionTipoCafe: CoffeeMachineState();
    data object cantidadAzucar : CoffeeMachineState();
    data object sirviendoCafe : CoffeeMachineState();
    data object recogerCafe : CoffeeMachineState();
    data object error : CoffeeMachineState();
}