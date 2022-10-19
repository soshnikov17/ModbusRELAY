import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.serial.*;

public class EnergyMeterModbus implements EnergyMeter, ModbusDevice{
    public static int SLAVEID;
    public static String PORT;
    EnergyMeterModbus(int SLAVEID, String PORT) {
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

    public void numberOfPhase(ModbusMaster master) throws ModbusIOException, ModbusProtocolException, ModbusNumberException {
        int count=0;
        int[] registerValues1 = master.readInputRegisters(SLAVEID, 0x1416, 1);
        for (int value : registerValues1) {
            if(value>10)
                count++;
            System.out.println("Фаза L1" + ", Ток: " + value+"x0.016А");
        }
        int[] registerValues2 = master.readInputRegisters(SLAVEID, 0x1418, 1);
        for (int value : registerValues2) {
            if(value>10)
                count++;
            System.out.println("Фаза L2" + ", Ток: " + value+"x0.016А");
        }
        int[] registerValues3 = master.readInputRegisters(SLAVEID, 0x141A, 1);
        for (int value : registerValues3) {
            if(value>10)
                count++;
            System.out.println("Фаза L3" + ", Ток: " + value+"x0.016А");
        }

        if (count>0 && count<2)
            System.out.println("Подключена одна фаза");
        else if (count>1 && count<3)
            System.out.println("Подключено две фазы");
        else if (count>2)
            System.out.println("Подключено три фазы");
        else
            System.out.println("Фазы не подключены");
    }
}
