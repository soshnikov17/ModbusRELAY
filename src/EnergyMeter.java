import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;

public interface EnergyMeter {
    void numberOfPhase(ModbusMaster master) throws  ModbusIOException, ModbusProtocolException, ModbusNumberException;
}
