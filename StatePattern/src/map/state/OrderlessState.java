package map.state;

import map.object.Role;

public class OrderlessState extends TemplateState {

    public OrderlessState(Role role) {
        super(role);
        this.countDown = 3;
    }

    @Override
    public void takeTurn(){
        // TODO: 隨機取得以下其中一種效果：1. 只能進行上下移動 2. 只能進行左右移動
        this.role.move();
        countDown();
    }
}
