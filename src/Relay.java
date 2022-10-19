import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;

public interface Relay {
    void RelayOn(ModbusMaster master, int adress) throws ModbusProtocolException, ModbusNumberException, ModbusIOException;
    void RelayOff(ModbusMaster master, int adress) throws ModbusProtocolException, ModbusNumberException, ModbusIOException;
    void RelayOnAll(ModbusMaster master) throws ModbusProtocolException, ModbusNumberException, ModbusIOException;
    void RelayOffAll(ModbusMaster master) throws ModbusProtocolException, ModbusNumberException, ModbusIOException;
    void RelayState(ModbusMaster master) throws ModbusProtocolException, ModbusNumberException, ModbusIOException;
    void RelayChangeState(ModbusMaster master, int startAdress) throws ModbusProtocolException, ModbusNumberException, ModbusIOException;
}
