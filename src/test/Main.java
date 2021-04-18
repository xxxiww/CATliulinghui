package test;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.HashSet;

/**
 * @author hky
 * @date 2021-04-11-0:25
 */
public class Main {

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        JPanel jPanel = new JPanel();
        JLabel jLabel = new JLabel();
        JButton jButton = new JButton("确定");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
        jf.setSize(800, 500);

        jPanel.add(jLabel);
        jPanel.add(jButton);

        a(jButton, jLabel);

        jf.setContentPane(jPanel);
        jf.setVisible(true);
    }

    public static void a(JButton btnNewButton, JLabel jLabel){
        //创建一个文件选择器对象
        JFileChooser choose = new JFileChooser();
        //设置文件不可多选
        choose.setMultiSelectionEnabled(false);
        //创建一个文件过滤器对象 过滤出JPG PNG格式的文件（这里过滤只是再文件选择器中显示过滤，而不是别的文件不能选择！！）
        FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg","png");
        //将过滤器设置进文件选择器
        choose.setFileFilter(filter);
        //设置一个打开文件选择器的触发组件 这个方法的返回值是int型 返回的是两个常量 1 0
        int returnVar = choose.showOpenDialog(btnNewButton);
        //判断returnVar的值，如果返回的是APPROVE_OPTION，则用户选择了YES或者OK，也就是确定了上传的文件
        //APPROVE_OPTION对应的常量为0
        if(returnVar == 0){
            //得到选择的文件
            File f = choose.getSelectedFile();
            //创建一个fileName得到选择文件的名字
            String fileName =f.getName();
            //lastIndexOf(".") 返回"."在文件名中最后一次出现的下标
            //substring(int index)从指定的index开始截取后面的字符串
            //比如： a.txt 最后一次出现的.下标是 1 substring(1)就是从下标1的位置开始截取 截取后的新字符串为 .txt
            //所以这里需要+1 才能只截取文件类型 txt
            String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
            //判断选择的文件是否是图片文件 必须排除不是的情况 不然后续操作会报错
            if(!(prefix.equals("jpg") || prefix.equals("png"))){
                JOptionPane.showMessageDialog(new JDialog(),":请选择.jpg 或 .png格式的图片");
                return;
            }
            //设置文件长传的目的路径 这里设置一个相对路径
            String path = "./images/";
            //创建一个file对象 File对象可以是一个具体文件 也可以是一个文件夹 这里我们用到的是文件夹
            File dir = new File(path);
            //获取该文件夹下的全部文件到fs
            File[] fs = dir.listFiles();
            //创建一个HashSet对象 用于存放path文件夹中所有文件的 "文件名"
            /**
             * 注意：这里用Set，而不用List的原因。  List Set Map 三大接口
             * List
             *         允许重复的对象
             *         可以插入多个null元素
             *         是一个有序容器，输出的顺序就是插入的顺序。
             *         重用的实现类有：ArrayList\LinkedList和Vector.
             *             ArrayList最为流行，它提供了使用索引的随意访问
             *             LinkedList则对于经常需要从List中添加或删除元素的场合更为合适
             * Set
             *         不允许重复对象，
             *         无序容器，你无法保证每个元素的存储顺序，TressSet通过Comparator和Comparable维护了一个排序顺序
             *         只允许一个null元素
             *         Set接口最流行的几个实现类是：HashSet、LinkedHashSet以及TreeSet
             *         最流行的是基于HashMap实现的HashSet
             *
             */
            HashSet<String> set = new HashSet<String>();
            //使用foreach将fs中的所有文件对象的文件名都add进set集合中
            for(File a : fs){
                set.add(a.getName());
            }
            //contains() 查看集合中是否包含指定的String数据
            if(set.contains(f.getName())){
                JOptionPane.showMessageDialog(new JDialog(),f.getName()+":文件已存在");
                return;
            }
            //创建文件的字节输入输出流
            FileInputStream input = null;
            FileOutputStream out = null;
            try {
                //读取文件选择器选择的文件f
                input = new FileInputStream(f);
                byte[] buffer = new byte[1024];
                //创建一个新的文件 名为f.getName() 的文件到 文件夹 path中
                File des = new File(path,f.getName());
                //输出新创建的这个文件des
                out = new FileOutputStream(des);
                int len = 0 ;
                //通过文件选择器对象拿到选择的文件.拿到该文件的绝对路径
                String absolutePath = choose.getSelectedFile().getAbsolutePath();
                //创建一个ImageIcon对象 传入图片文件的绝对路径 通过这个对象得到图片的 长 宽
                ImageIcon imageIcon = new ImageIcon(absolutePath);
                int hight = imageIcon.getIconHeight();
                int witdh = imageIcon.getIconWidth();
                //判断hight和witdh是否符合要求 符合要求的进行上传操作，也就是将图片文件写到指定的文件夹中
                if(hight < 5000 && witdh <5000){
                    /**
                     * read()方法：从输入流中读取数据的下一个字节
                     * read(byte[] b)方法：从输入流中读取以定数量的字节，并将其存储在缓冲区数组b中
                     *
                     * write()系列方法进行写操作时并不一定直接将所写的内容写出，而先将需要写出的内容放到输出缓冲区
                     * 直到缓冲区满，调用flush()方法刷新流 或 调用close()方法关闭流时才真正输出。
                     * 这样处理可以减少实际的写出次数，提高系统效率
                     * 如果需要写出的内容立即输出，需要在完成write()方法后调用flush()方法刷新流，否则程序可能不能正常工作
                     *
                     */
                    while((len = input.read(buffer)) != -1){
                        out.write(buffer, 0, len);
                    }
                    //将写的内容从缓存中立即输出
                    out.flush();
                    /**
                     * 注意：
                     *         我们之前的ImageIcon对象指向的是用户上传的文件的绝对路径 而不是我们服务器(也就是我们指定的目标文件夹)的文件
                     *         虽然图片是一样的 但是我们上传成功后 显示的应该是服务器上的图片！而不是用户本地的。
                     */
                    //拿到Image对象，设置Image的自适应高度和宽度 SCALE_DEFAULT为自适应属性
                    imageIcon = new ImageIcon(path+"\\"+f.getName());
                    Image img = imageIcon.getImage();
                    img = img.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                    //将自适应调整后的图片设置到imageIcon，再将ImageIcon设置到需要显示这个图片的组件中 在这里是设置进标签中
                    imageIcon.setImage(img);
                    jLabel.setIcon(imageIcon);
                    JOptionPane.showMessageDialog(null, "上传成功！", "提示",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "请上传250*250大小的图片!", "提示",JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(null, "上传失败！", "提示",JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }catch (IOException e) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(null, "上传失败！", "提示",JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } finally{
                try {
                    out.close();
                    input.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

}
