import React, { useEffect, useState } from "react";
import {useLocation, useNavigate, useParams} from "react-router-dom";
import MyButton from "../components/MyButton";
import MyHeader from "../components/MyHeader";
import {call} from "../service/ApiService";
import {emotionList} from "../util/emotion";

const Book = () => {
    //const { isbn } = useParams();
    const { id } = useParams();
    const navigate = useNavigate();
    const [data, setData] = useState();

    const location = useLocation();
    const isReviewPage = location.state;

    useEffect(() => {
        if(!isReviewPage && id){
           // if (isbn) {
                let param = {isbn: id}
                call("/api/book/detail","GET", param)
                    .then((res) => {
                        setData(res.items[0])
                    });
          //  }
        }else{
           // if (id) {
                call(`/api/review/${id}`,"GET", {})
                    .then((res) => {
                        setData(res)
                    });
           // }
        }

    }, [id]);

    if (!data) {
        return <div className="BookPage">로딩중입니다...</div>;
    } else {
        const curEmotionData = emotionList.find((it) => parseInt(it.emotion_id) === parseInt(data.score));

        return (
            <div className="BookPage">
                <MyHeader
                    headText={!isReviewPage ? "도서 상세" : "기록 상세"}
                    leftChild={<MyButton text={'< 뒤로가기'} onClick={() => navigate(-1)} />}
                    rightChild={
                        !isReviewPage ?
                        <MyButton text={'기록 작성하기'} type={'positive'} onClick={() => navigate('/new',{state: data})} />
                        :
                        <MyButton text={'기록 수정하기'} type={'positive'} onClick={() => navigate(`/edit/${id}`)} />
                    }
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
                        {
                            isReviewPage &&
                            <div style={{marginTop: "-30px"}}>
                                <h4>{"내 평가"}</h4>
                                <div className="score_img_wrapper">
                                    <img src={`/assets/emotion${data.score}.png`} />
                                    <span>{curEmotionData.emotion_descript}</span>
                                </div>
                            </div>
                        }
                        <h4>{!isReviewPage ? `책 소개` : `내 기록`}</h4>

                        <div className="book_content_wrapper">
                            <pre>{!isReviewPage ? data.description : data.content}</pre>
                        </div>
                        {isReviewPage && <p style={{marginLeft: "335px"}}>{data.createdDate} 에 작성된 기록입니다.</p>}
                    </section>
                </article>
            </div>
        )
    }

    /*return (
        <div>
            <h1>Book</h1>
            <p>이곳은 일기 상세 페이지 입니다.</p>
        </div>
    );*/
};

export default Book;