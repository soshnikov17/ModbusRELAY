import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.serial.*;

public class RelayModbus implements Relay, ModbusDevice {

    public static int SLAVEID;
    public static String PORT;

    RelayModbus(int SLAVEID, String PORT) {
        this.SLAVEID = SLAVEID;
        this.PORT = PORT;
    }

    public ModbusMaster initialModbusMaster() throws ModbusIOException, SerialPortException {
        SerialParameters sp = new SerialParameters();
        sp.setDevice(PORT);
        sp.setBaudRate(SerialPort.BaudRate.BAUD_RATE_9600);
        sp.setDataBits(8);
        sp.setParity(SerialPort.Parity.NONE);
        sp.setStopBits(2);
        SerialUtils.setSerialPortFactory(new SerialPortFactoryJSSC());
        com.intelligt.modbus.jlibmodbus.master.ModbusMaster master = ModbusMasterFactory.createModbusMasterRTU(sp);
        master.connect();
        return master;
    }

    public void RelayOn(ModbusMaster master, int adress) throws ModbusProtocolException, ModbusNumberException, ModbusIOException {
        master.writeSingleCoil(SLAVEID, adress, true);
    }

    public void RelayOff(ModbusMaster master, int adress) throws ModbusProtocolException, ModbusNumberException, ModbusIOException {
        master.writeSingleCoil(SLAVEID, adress, false);
    }

    public void RelayOnAll(ModbusMaster master) throws ModbusProtocolException, ModbusNumberException, ModbusIOException {
        master.writeSingleCoil(SLAVEID, 0, true);
        master.writeSingleCoil(SLAVEID, 1, true);
        master.writeSingleCoil(SLAVEID, 2, true);
        master.writeSingleCoil(SLAVEID, 3, true);
        master.writeSingleCoil(SLAVEID, 4, true);
        master.writeSingleCoil(SLAVEID, 5, true);
    }

    public void RelayOffAll(ModbusMaster master) throws ModbusProtocolException, ModbusNumberException, ModbusIOException {
        master.writeSingleCoil(SLAVEID, 0, false);
        master.writeSingleCoil(SLAVEID, 1, false);
        master.writeSingleCoil(SLAVEID, 2, false);
        master.writeSingleCoil(SLAVEID, 3, false);
        master.writeSingleCoil(SLAVEID, 4, false);
        master.writeSingleCoil(SLAVEID, 5, false);
    }

    public void RelayState(ModbusMaster master) throws ModbusProtocolException, ModbusNumberException, ModbusIOException {
        int count = 0;
        boolean[] registerValues = master.readCoils(SLAVEID, 0, 6);
        for (boolean value : registerValues) {
            if (count < 6)
                System.out.println("Adress: " + count + " Value: " + value);
            count++;
        }
    }
    public void RelayChangeState(ModbusMaster master, int startAdress) throws ModbusProtocolException, ModbusNumberException, ModbusIOException {
        boolean[] registerValues = master.readCoils(SLAVEID, startAdress, 1);
        if (registerValues[startAdress]==true)
            master.writeSingleCoil(SLAVEID, startAdress, false);
        else
            master.writeSingleCoil(SLAVEID, startAdress, true);
    }
}