import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.serial.*;

public class Main {
    static public void main(String[] arg) throws ModbusProtocolException, SerialPortException, ModbusNumberException, ModbusIOException {
/*      RelayModbus relay= new RelayModbus(18,"COM4");//создаём объект реле с заданным SlaveId и портом
        com.intelligt.modbus.jlibmodbus.master.ModbusMaster master=relay.initialModbusMaster();//инициализируем мастер устройство для управления реле
        relay.RelayChangeState(master, 0);
        relay.RelayOn(master, 5);*/
        EnergyMeterModbus energyMeter= new EnergyMeterModbus(242, "COM4");
        com.intelligt.modbus.jlibmodbus.master.ModbusMaster master=energyMeter.initialModbusMaster();
        energyMeter.numberOfPhase(master);
    }
}



