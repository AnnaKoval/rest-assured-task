package common;

import dto.ForecastDTO;

import java.io.*;

public class Serializator {

    public void serialization(ForecastDTO forecastDTO) {
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fos = new FileOutputStream("D:/forecast.data");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(forecastDTO);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    public ForecastDTO deserialization() throws InvalidObjectException {
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream("D:/forecast.data");
            ois = new ObjectInputStream(fis);
            ForecastDTO forecastDTO = (ForecastDTO) ois.readObject();
            return forecastDTO;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        throw new InvalidObjectException("Can't read object");
    }
}
