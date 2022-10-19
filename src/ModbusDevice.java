import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.serial.SerialPortException;

public interface ModbusDevice  {
    ModbusMaster initialModbusMaster() throws ModbusIOException, SerialPortException;
}
