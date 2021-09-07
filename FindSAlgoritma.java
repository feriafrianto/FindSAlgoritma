import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FindSAlgoritma {
    public static void main(String[] args) {
        // Deklarasi Variable yang dibutuhkan
        ArrayList<String[]> resultData = new ArrayList<>();
        String[][] attributes = null;
        String[] hypotesis = new String[10];
        String[] testing = new String[10];
        String TrainingData = null,target=null;
        String param1="",param2="";
        boolean Flag;

        // Menu Aplikasi
        Scanner scan = new Scanner(System.in);
        line();
        System.out.println("Find-S Algorithm");
        line();
        System.out.println("1. Pengenalan buah");
        System.out.println("2. Deteksi penyakit hipertensi");
        line();
        System.out.print("Masukan Pilihan : ");
        int pilih = scan.nextInt();
        line(); 
        switch (pilih) {
            case 1:
                TrainingData = "DataBuah.csv";
                target = "Pisang";
                param1 = "Panjang";
                param2 = "Lebar";
                break;    
            case 2:
                TrainingData = "DataHipertensi.csv";
                target = "Ya";
                param1 = "Umur";
                param2 = "Kegemukan";
                break;
        }
        
        // Membaca File Training
        try {
            File file = new File(TrainingData); 
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            String str = "";

            while (str != null) {
                str = br.readLine();

                if (str != null) 
                resultData.add(str.split(","));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan");
        } catch (IOException e) {
            System.out.println("Kesalahan saat membaca file");
        } finally {
            if (resultData.size() > 0) {
                int rowSize = resultData.size();
                int columnSize = resultData.get(0).length;

                attributes = new String[rowSize][columnSize];
                attributes = resultData.toArray(attributes);
            }
        }
        // Menghitung atribut
        int attributesLength = attributes.length;

        // Target dari hipotesis
        System.out.println("Target Hipotesis adalah \"" + target + "\".");
        System.out.println("");

        // Cek apakah atribut ada isinya
        if (attributesLength > 0) { 
            int instanceLength = attributes[0].length;

            // Cek apakah atribut minimal memiliki 2 instance
            if (instanceLength > 1) { 
                // Jumlah instance dikurangi satu agar tidak mengikutkan target
                instanceLength--; 

                // Membuat array hipotesis sebesar jumlah instance
                hypotesis = new String[instanceLength]; 
                int attributeFirstIndex = 0;

                // Mengambil dataset pertama yang memiliki target yang diinginkan
                for (int i = 0; i < attributesLength; i++) {
                    if (attributes[i][instanceLength].equals(target)) {
                        attributeFirstIndex = i;
                        break;
                    }
                }

                // Mengcopy array dataset pertama di array hipotesis
                System.arraycopy(attributes[attributeFirstIndex], 0, hypotesis, 0, instanceLength);

                // Proses membuat instance yang berbeda disetiap dataset menjadi '?'
                for (int i = 1; i < attributesLength; i++) {
                    if (attributes[i][instanceLength].equals(target)) {
                        for (int j = 0; j < instanceLength; j++) {
                            if (!hypotesis[j].equals(attributes[i][j]))
                                hypotesis[j] = "?";
                        }
                    }
                }
                // Menampilkan hasil hipotesis
                System.out.print("Hasil Hipotesis : <");
                for (String h : hypotesis){
                    System.out.print(h);
                    System.out.print(",");
                }
                System.out.println(">");
            } else
                System.out.println("Instance harus lebih dari 1, dan kolom terakhir harus menjadi target...");
        } else
            System.out.println("Attribute Kosong");
        
        // Testing user input 2 inputan
        line();
        scan.nextLine(); 
        System.out.print("Masukan "+ param1 +" : ");
        for(int k=0; k<2; k++) {
            testing[k] = scan.nextLine(); 
            if (k<1) {
                System.out.print("Masukan "+ param2 +" : ");
            }  
        }
        line();
        Flag = true; 
        // Mengecek setiap attribute pada data testing yang telah diinputkan
        for (int i = 0; i < 2; i++) {
                // Mengecek apakah data hipotesa mirip dengan data testing yang telah diinputkan 
                if (!hypotesis[i].equals("?") && !testing[i].equals(hypotesis[i]))
                    Flag = false;

                    testing[i] += (i == (testing.length - 1) ? " " : ", ");
                System.out.print(testing[i]);
            }
            // Menampilkan hasil dari kesimpulan
            if (Flag)
                if (pilih==1) {
                    System.out.println("= Pisang");
                } else {
                    System.out.println("= Hipertensi");
                }
                
            else {
                if (pilih==1) {
                    System.out.println("= Apel");
                } else {
                    System.out.println("= Tidak Hipertensi");
                }
            }
            
        line();
        scan.close();
    }

    public static void line(){
        System.out.println("----------------------------------");
    }
}