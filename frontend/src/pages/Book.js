import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import MyButton from "../components/MyButton";
import MyHeader from "../components/MyHeader";
import {call} from "../service/ApiService";

const Book = () => {
    const { isbn } = useParams();
    const navigate = useNavigate();
    const [data, setData] = useState();

    useEffect(() => {
        if (isbn) {
            let param = {isbn: isbn}
            call("/api/book/detail","GET", param)
                .then((res) => {
                    setData(res.items[0])
                });
        }
    }, [isbn]);

    if (!data) {
        return <div className="BookPage">로딩중입니다...</div>;
    } else {
        //const curEmotionData = emotionList.find((it) => parseInt(it.emotion_id) === parseInt(data.emotion));
        return (
            <div className="BookPage">
                <MyHeader
                    headText="도서 상세"
                    leftChild={<MyButton text={'< 뒤로가기'} onClick={() => navigate(-1)} />}
                    rightChild={<MyButton text={'기록 작성하기'} type={'positive'} onClick={() => navigate('/new',{state: data})} />}
                />
                <article>
                    <section>
                        <h4>{data.title}</h4>
                        <div className="book_img_wrapper">
                            <img src={data.image} />
                        </div>
                        <a href={data.link}>자세히 보기</a>
                        <div className="book_info_wrapper">
                            <div className="book_info">
                                <h4>출판사</h4>
                                <p>{data.publisher}</p>
                            </div>
                            <div className="book_info" style={{marginLeft: "20px"}}>
                                <h4>저자</h4>
                                <p>{data.author}</p>
                            </div>
                        </div>
                        <h4>책 소개</h4>
                        <div className="book_content_wrapper">
                            <pre>{data.description}</pre>
                        </div>
                    </section>
                </article>
            </div>
        )
    }

    return (
        <div>
            <h1>Book</h1>
            <p>이곳은 일기 상세 페이지 입니다.</p>
        </div>
    );
};

export default Book;