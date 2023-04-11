import { useNavigate } from "react-router-dom";

import MyHeader from './../components/MyHeader';
import MyButton from './../components/MyButton';
import BookList from './../components/BookList';

const Home = () => {

    const headText = "도서 검색";
    const navigate = useNavigate();

    return (
        <div>
            <MyHeader headText={headText}
                rightChild={<MyButton text={"내 독서 기록"} type={'positive'} onClick={() => navigate('/review')} />}
            />
            <BookList />
        </div>
    );
};

export default Home;