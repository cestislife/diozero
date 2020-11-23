package com.diozero.internal.provider.builtin.i2c;

import com.diozero.util.LibraryLoader;

public class NativeI2C {
	static {
		LibraryLoader.loadSystemUtils();
	}
	
	static final int I2C_FUNC_I2C                    = 0x00000001;
	static final int I2C_FUNC_10BIT_ADDR             = 0x00000002;
	static final int I2C_FUNC_PROTOCOL_MANGLING      = 0x00000004; /* I2C_M_IGNORE_NAK etc. */
	static final int I2C_FUNC_SMBUS_PEC              = 0x00000008;
	static final int I2C_FUNC_NOSTART                = 0x00000010; /* I2C_M_NOSTART */
	static final int I2C_FUNC_SMBUS_BLOCK_PROC_CALL  = 0x00008000; /* SMBus 2.0 */
	static final int I2C_FUNC_SMBUS_QUICK            = 0x00010000;
	static final int I2C_FUNC_SMBUS_READ_BYTE        = 0x00020000;
	static final int I2C_FUNC_SMBUS_WRITE_BYTE       = 0x00040000;
	static final int I2C_FUNC_SMBUS_READ_BYTE_DATA   = 0x00080000;
	static final int I2C_FUNC_SMBUS_WRITE_BYTE_DATA  = 0x00100000;
	static final int I2C_FUNC_SMBUS_READ_WORD_DATA   = 0x00200000;
	static final int I2C_FUNC_SMBUS_WRITE_WORD_DATA  = 0x00400000;
	static final int I2C_FUNC_SMBUS_PROC_CALL        = 0x00800000;
	static final int I2C_FUNC_SMBUS_READ_BLOCK_DATA  = 0x01000000;
	static final int I2C_FUNC_SMBUS_WRITE_BLOCK_DATA = 0x02000000;
	static final int I2C_FUNC_SMBUS_READ_I2C_BLOCK   = 0x04000000; /* I2C-like block xfer  */
	static final int I2C_FUNC_SMBUS_WRITE_I2C_BLOCK  = 0x08000000; /* w/ 1-byte reg. addr. */
	
	/* smbus_access read or write markers */
	public static final byte I2C_SMBUS_READ = 1;
	public static final byte I2C_SMBUS_WRITE = 0;

	static native int getFuncs(int fd);
	static native int selectSlave(int fd, int deviceAddress, boolean force);

	// System Management Bus (SMBus) commands
	static native int smbusOpen(String adapter, int deviceAddress, boolean force);
	static native void smbusClose(int fd);
	static native int writeQuick(int fd, byte bit);
	static native int readByte(int fd);
	static native int writeByte(int fd, byte value);
	static native int readBytes(int fd, int rxLength, byte[] rxData);
	static native int writeBytes(int fd, int txLength, byte[] txData);
	static native int readByteData(int fd, int registerAddress);
	static native int writeByteData(int fd, int registerAddress, byte value);
	static native int readWordData(int fd, int registerAddress);
	static native int writeWordData(int fd, int registerAddress, short value);
	static native int processCall(int fd, int registerAddress, short value);
	static native int readBlockData(int fd, int registerAddress, byte[] rxData);
	static native int writeBlockData(int fd, int registerAddress, int txLength, byte[] txData);
	static native int blockProcessCall(int fd, int registerAddress, int txLength, byte[] txData, byte[] rxData);
	static native int readI2CBlockData(int fd, int registerAddress, int rxLength, byte[] rxData);
	static native int writeI2CBlockData(int fd, int registerAddress, int txLength, byte[] txData);
}