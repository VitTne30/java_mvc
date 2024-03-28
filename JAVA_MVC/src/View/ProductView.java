package View;

import Controller.ProductController;
import Swing.Button;
import Swing.MyTextField;
import Swing.Table;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author PV
 */
public class ProductView extends JPanel {
    private JScrollPane scroll;
    private Table table_product;
    private JLabel title, name, author, id, id_nhaxb, price, number, category;
    private final MyTextField jtf_search, jtf_name, jtf_author, jtf_id, jtf_price, jtf_number;
    private final Button add, save, delete, change, search, put_excel;
    private final JComboBox Jcb_category, Jcb_publisher;

    public ProductView() {
        
        setBounds(0, 0, 800, 800);
        setVisible(true);
        Color xanh = new Color(179, 224, 255);
        this.setLayout(null);

        Font chuTo = new Font("Times New Roman", Font.ITALIC, 36);
        Font chuNho = new Font("Arial", Font.CENTER_BASELINE, 10);

        //top side
        title = new JLabel("Quản lý sách");
        title.setFont(chuTo);

        title.setBounds(300, 0, 200, 50);
        add(title);

        //table
        table_product = new Table();
        scroll = new JScrollPane();
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        table_product.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Mã", "Tiêu đề", "Danh mục", "Tác giả", "Số lượng", "Giá", "NXB"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        table_product.setFont(chuNho);
        TableColumnModel columnModel = table_product.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(170);
        columnModel.getColumn(4).setPreferredWidth(100);
        columnModel.getColumn(5).setPreferredWidth(80);
        columnModel.getColumn(6).setPreferredWidth(170);
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setResizable(false);
        }
        JTableHeader header = table_product.getTableHeader();
        header.setReorderingAllowed(false);
        header.setFont(chuNho);
        scroll.setViewportView(table_product);
        scroll.setBounds(0, 50, 800, 450);
        scroll.getViewport().setBackground(Color.WHITE);
        this.add(scroll);

        //Label va textfield
        Font fontLB = new Font("sansserif", Font.BOLD, 15);
        id = new JLabel("ID: ");
        id.setFont(fontLB);
        id.setBounds(10, 560, 50, 40);
        this.add(id);

        name = new JLabel("Tiêu đề:");
        name.setFont(fontLB);
        name.setBounds(285, 560, 100, 40);
        this.add(name);

        author = new JLabel("Tác giả:");
        author.setFont(fontLB);
        author.setBounds(10, 610, 80, 40);
        this.add(author);

        price = new JLabel("Giá tiền:");
        price.setFont(fontLB);
        price.setBounds(285, 610, 100, 40);
        this.add(price);

        id_nhaxb = new JLabel("NXB: ");
        id_nhaxb.setFont(fontLB);
        id_nhaxb.setBounds(10, 660, 80, 40);
        this.add(id_nhaxb);

        category = new JLabel("Danh mục:");
        category.setFont(fontLB);
        category.setBounds(285, 660, 80, 40);
        this.add(category);

        number = new JLabel("Số lượng:");
        number.setFont(fontLB);
        number.setBounds(10, 710, 80, 40);
        this.add(number);

        jtf_id = new MyTextField();
        jtf_id.setFont(fontLB);
        jtf_id.setHint("ID sản phẩm");
        jtf_id.setBounds(75, 560, 200, 40);
        this.add(jtf_id);

        jtf_name = new MyTextField();
        jtf_name.setFont(fontLB);
        jtf_name.setHint("Tên sản phẩm");
        jtf_name.setBounds(375, 560, 200, 40);
        this.add(jtf_name);

        jtf_author = new MyTextField();
        jtf_author.setFont(fontLB);
        jtf_author.setHint("Tác giả");
        jtf_author.setBounds(75, 610, 200, 40);
        this.add(jtf_author);

        jtf_price = new MyTextField();
        jtf_price.setFont(fontLB);
        jtf_price.setHint("Nhập giá tiền");
        jtf_price.setBounds(375, 610, 200, 40);
        this.add(jtf_price);

        Jcb_publisher = new JComboBox();
        Jcb_publisher.setFont(fontLB);
        Jcb_publisher.setBounds(75, 660, 200, 40);
        this.add(Jcb_publisher);

        Jcb_category = new JComboBox();
        Jcb_category.setFont(fontLB);
        Jcb_category.setBounds(375, 660, 200, 40);
        this.add(Jcb_category);

        jtf_number = new MyTextField();
        jtf_number.setFont(fontLB);
        jtf_number.setHint("Số lượng");
        jtf_number.setBounds(85, 710, 190, 40);
        this.add(jtf_number);

        jtf_search = new MyTextField();
        jtf_search.setFont(fontLB);
        jtf_search.setHint("Nhập tên sản phẩm...");
        jtf_search.setBounds(10, 510, 430, 40);
        this.add(jtf_search);

        //Buttons
        Font buttonF = new Font("Helvetica", 1, 20);
        add = new Button();
        add.setFont(buttonF);
        add.setText("Thêm");
        add.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/add_book_1.png")));
        add.setBounds(610, 510, 120, 40);
        this.add(add);

        change = new Button();
        change.setFont(buttonF);
        change.setText("Sửa");
        //add.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/add_book_1.png")));
        change.setBounds(610, 560, 120, 40);
        this.add(change);

        save = new Button();
        save.setFont(buttonF);
        save.setText("Lưu");
        //add.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/add_book_1.png")));
        save.setBounds(610, 610, 120, 40);
        this.add(save);

        delete = new Button();
        delete.setFont(buttonF);
        delete.setText("Xóa");
        //add.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/add_book_1.png")));
        delete.setBounds(610, 660, 120, 40);
        this.add(delete);

        put_excel = new Button();
        put_excel.setFont(buttonF);
        put_excel.setText("Xuất Excel");
        //add.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/add_book_1.png")));
        put_excel.setBounds(610, 710, 120, 40);
        this.add(put_excel);

        search = new Button();
        search.setFont(buttonF);
        search.setText("Tìm kiếm");
        //add.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/add_book_1.png")));
        search.setBounds(450, 510, 120, 40);
        search.setForeground(xanh);
        this.add(search);

        this.repaint();
    }

    public static void main(String Arg[]) throws SQLException {
        ProductView one = new ProductView();
        ProductController two = new ProductController(one);
    }

    public JScrollPane getScroll() {
        return scroll;
    }

    public Table getTable_product() {
        return table_product;
    }

    public MyTextField getJtf_search() {
        return jtf_search;
    }

    public MyTextField getJtf_name() {
        return jtf_name;
    }

    public MyTextField getJtf_author() {
        return jtf_author;
    }

    public MyTextField getJtf_id() {
        return jtf_id;
    }

    public JComboBox getJcb_category() {
        return Jcb_category;
    }

    public JComboBox getJcb_publisher() {
        return Jcb_publisher;
    }

    public MyTextField getJtf_price() {
        return jtf_price;
    }

    public MyTextField getJtf_number() {
        return jtf_number;
    }

    public Button getAdd() {
        return add;
    }

    public Button getSave() {
        return save;
    }

    public Button getDelete() {
        return delete;
    }

    public Button getChange() {
        return change;
    }

    public Button getSearch() {
        return search;
    }

    public Button getPut_excel() {
        return put_excel;
    }

}
