import { useNavigate } from "react-router-dom";

import MyHeader from './../components/MyHeader';
import MyButton from './../components/MyButton';
import BookList from './../components/BookList';

const Review = () => {

    const headText = "내 독서 기록";
    const navigate = useNavigate();

    return (
        <div>
            <MyHeader headText={headText}
                rightChild={<MyButton text={"도서 검색"} type={'positive'} onClick={() => navigate('/')} />}
            />
            <BookList isReviewPage={true} />
        </div>
    );
};

export default Review;