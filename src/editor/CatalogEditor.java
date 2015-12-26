package editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CatalogEditor extends JFrame {
    
    private CatalogController controller;
    
    private JPanel content = new JPanel();
    private JScrollPane tableView;
    private JTable table;
    private JButton addAlbumButton;
    private JButton deleteAlbumButton;
    
    public CatalogEditor() {
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(content);
        addAlbumButton = new JButton("Add album");
        deleteAlbumButton = new JButton("Delete album");
        setActionListeners();
    }

    public void setController(CatalogController controller) {
        this.controller = controller;
        table = new JTable(controller.getModel());
        tableView = new JScrollPane(table);
        content.add(tableView);
        content.add(addAlbumButton);
        content.add(deleteAlbumButton);
        pack();
        repaint();
    }
    
    public final void setActionListeners() {
        
        addAlbumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.addAlbum();
            }
        });
        
        deleteAlbumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0)
                    controller.deleteAlbum(row);
            }
        });
    }
}